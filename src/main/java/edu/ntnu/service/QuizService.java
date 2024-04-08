package edu.ntnu.service;

import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dao.questions.TextInputQuestionDAO;
import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.mapper.QuizMapper;
import edu.ntnu.repository.QuizRepository;
import edu.ntnu.repository.questions.DragDropQuestionDAORepository;
import edu.ntnu.repository.questions.MultipleChoiceQuestionDAORepository;
import edu.ntnu.repository.questions.TextInputQuestionDAORepository;
import edu.ntnu.repository.questions.TrueFalseQuestionDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service class for Quiz objects.
 */
@Service
public class QuizService {
  private final QuizRepository quizRepository;
  private final MultipleChoiceQuestionDAORepository multipleChoiceRepository;
  private final TextInputQuestionDAORepository textInputRepository;
  private final DragDropQuestionDAORepository dragDropRepository;
  private final TrueFalseQuestionDAORepository trueFalseRepository;
  private final QuestionService questionService;
  private final QuizMapper quizMapper;
  private final Logger logger = Logger.getLogger(QuizService.class.getName());

  /**
   * Constructor for the QuizService.
   * @param quizRepository the repository for the QuizDAO objects
   * @param multipleChoiceRepository the repository for the MultipleChoiceQuestionDAO objects
   * @param textInputRepository the repository for the TextInputQuestionDAO objects
   * @param dragDropRepository the repository for the DragDropQuestionDAO objects
   * @param trueFalseRepository the repository for the TrueFalseQuestionDAO objects
   * @param quizMapper the mapper for the Quiz objects
   * @param questionService the service for the Question objects
   */
  @Autowired
  public QuizService(
      QuizRepository quizRepository,
      MultipleChoiceQuestionDAORepository multipleChoiceRepository,
      TextInputQuestionDAORepository textInputRepository,
      DragDropQuestionDAORepository dragDropRepository,
      TrueFalseQuestionDAORepository trueFalseRepository,
      QuizMapper quizMapper,
      QuestionService questionService)
  {
    this.quizRepository = quizRepository;
    this.multipleChoiceRepository = multipleChoiceRepository;
    this.textInputRepository = textInputRepository;
    this.dragDropRepository = dragDropRepository;
    this.trueFalseRepository = trueFalseRepository;
    this.quizMapper = quizMapper;
    this.questionService = questionService;
  }

  /**
   * Method to get a quiz by quizId from the database.
   * If the quiz is found, the status code is 200.
   * If the quiz is not found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @param quizId The quizId of the quiz to get.
   * @return ResponseEntity with the Quiz object and status code.
   */
  public ResponseEntity<QuizDTO> getQuiz(Long quizId) {
   try {
    QuizDAO quizDAO = quizRepository.findByQuizId(quizId);
    QuizDTO quizDTO = quizMapper.toQuizDTO(quizDAO);

    if (quizDTO != null) {
      logger.info("Quiz with id " + quizId + " found. Returning quiz.");
      return ResponseEntity.ok(quizDTO);
    } else {
      logger.info("Quiz with id " + quizId + " not found.");
      return ResponseEntity.notFound().build();
    }
  } catch (Exception e) {
    logger.severe("An error occurred while getting quiz with id " + quizId + ": " + e.getMessage());
    return ResponseEntity.status(500).build();
  }
  }

  /**
   * Method to get all quizzes from the database.
   * If quizzes are found, the status code is 200.
   * If no quizzes are found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @return ResponseEntity with a list of Quiz objects and status code.
   */
  public ResponseEntity<Iterable<QuizDTO>> getAllQuizzes(String username) {
    try {
      // Get all quizzes from the database
      Iterable<QuizDAO> quizzes = quizRepository.findAllByUserDAO_Username(username);

      // Convert quizzes to DTOs
      if (quizzes != null) {
        List<QuizDTO> quizDTOs = new ArrayList<>();
        for (QuizDAO quizDAO : quizzes) {
          quizDTOs.add(quizMapper.toQuizDTO(quizDAO));
        }

        int numQuizzes = quizDTOs.size();
        logger.info(numQuizzes + " quizzes found. Returning quizzes.");
        return ResponseEntity.ok(quizDTOs);
      } else {
        logger.info("No quizzes found.");
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe("An error occurred while getting all quizzes: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Method to create a quiz from a QuizDTO object.
   * Saves the quiz to the database.
   * If the quiz is created successfully, the status code is 200.
   * If an error occurs, the status code is 500.
   *
   * @param quizDTO The QuizDTO object to create the quiz from.
   * @return ResponseEntity with the Quiz object and status code.
   */
  public ResponseEntity<QuizDTO> createQuiz(QuizDTO quizDTO) {
    try {
      // Map DTO to entity without ID. ID is generated by database.
      QuizDAO quizDAO = quizMapper.toDAOWithoutId(quizDTO);

      // Save quiz to database
       QuizDAO savedQuizDAO = quizRepository.save(quizDAO);


      // Save questions associated with the quiz to the database
      List<QuestionDTO> questions = quizDTO.getQuestions();
      if (questions != null) {
        for (QuestionDTO questionDTO : questions) {
          questionDTO.setQuizId(savedQuizDAO.getQuizId());
          questionService.createQuestion(questionDTO);
        }
        logger.info("Questions to quiz " + savedQuizDAO.getQuizName() + " saved to database.");
      }

      logger.info("Quiz \"" + quizDAO.getQuizName() + "\" created successfully.");
      QuizDTO savedQuizDTO = quizMapper.toQuizDTO(savedQuizDAO);
      return ResponseEntity.ok(savedQuizDTO);
    } catch (DataIntegrityViolationException e) {
      logger.severe("Data integrity violation occurred while creating quiz: " + e.getMessage());
      logger.severe("One or more fields in the quiz are invalid.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (Exception e) {
      System.out.println(e.getClass().getSimpleName());
      logger.severe("An error occurred while creating quiz: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Method to update a quiz from a QuizDTO object.
   * Updates the quiz in the database.
   * If the quiz is updated successfully, the status code is 200.
   * If an error occurs, the status code is 500.
   *
   * @param quizId The quizId of the quiz to delete.
   * @return ResponseEntity with the Quiz object and status code.
   */
  public ResponseEntity<String> deleteQuiz(Long quizId) {
    try {
      deleteQuizFromQuizId(quizId);
      logger.info("Quiz with id " + quizId + " deleted successfully.");
      return ResponseEntity.ok("Quiz deleted successfully.");
    } catch (Exception e) {
      logger.severe("An error occurred while deleting quiz with id " + quizId + ": " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Method to delete a quiz from the database.
   * Deletes the quiz and all questions associated with the quiz.
   *
   * @param quizId The quizId of the quiz to delete.
   */
  public void deleteQuizFromQuizId(Long quizId) {
    try {
      // Delete questions associated with the quiz
      List<MultipleChoiceQuestionDAO> multipleChoiceQuestionDAOS = (List<MultipleChoiceQuestionDAO>) multipleChoiceRepository.findAllByQuizDAO_QuizId(quizId);
      List<TextInputQuestionDAO> textInputQuestions = (List<TextInputQuestionDAO>) textInputRepository.findAllByQuizDAO_QuizId(quizId);
      List<DragDropQuestionDAO> dragDropQuestions = (List<DragDropQuestionDAO>) dragDropRepository.findAllByQuizDAO_QuizId(quizId);
      List<TrueFalseQuestionDAO> trueFalseQuestions = (List<TrueFalseQuestionDAO>) trueFalseRepository.findAllByQuizDAO_QuizId(quizId);

      if (multipleChoiceQuestionDAOS != null) {
        multipleChoiceRepository.deleteAll(multipleChoiceQuestionDAOS);
      }
      if (textInputQuestions != null) {
        textInputRepository.deleteAll(textInputQuestions);
      }
      if (dragDropQuestions != null) {
        dragDropRepository.deleteAll(dragDropQuestions);
      }
      if (trueFalseQuestions != null) {
        trueFalseRepository.deleteAll(trueFalseQuestions);
      }

      // Delete quiz
      quizRepository.deleteByQuizId(quizId);
    } catch (Exception e) {
      logger.severe("An error occurred while deleting quiz with id " + quizId + ": " + e.getMessage());
      throw e;
    }
  }

  /**
   * Method to get all public quizzes from the database.
   * If public quizzes are found, the status code is 200.
   * If no public quizzes are found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @return ResponseEntity with a list of Quiz objects and status code.
   */
  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzes() {
    try {
      // Get all public quizzes from the database
      Iterable<QuizDAO> quizzes = quizRepository.findAllByIsPublic(true);

      // Convert quizzes to DTOs
      if (quizzes != null) {
        List<QuizDTO> quizDTOs = new ArrayList<>();
        for (QuizDAO quizDAO : quizzes) {
          quizDTOs.add(quizMapper.toQuizDTO(quizDAO));
        }

        int numQuizzes = quizDTOs.size();
        logger.info(numQuizzes + " public quizzes found. Returning quizzes.");
        return ResponseEntity.ok(quizDTOs);
      } else {
        logger.info("No public quizzes found.");
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe("An error occurred while getting all public quizzes: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Method to update a quiz from a QuizDTO object.
   * Updates the quiz in the database.
   * If the quiz is updated successfully, the status code is 200.
   * If an error occurs, the status code is 500.
   *
   * @param quizDTO The QuizDTO object to update the quiz from.
   * @return ResponseEntity with the Quiz object and status code.
   */
  public ResponseEntity<QuizDTO> updateQuiz(QuizDTO quizDTO) {
    try {
      deleteQuizFromQuizId(quizDTO.getQuizId());
      ResponseEntity<QuizDTO> response = createQuiz(quizDTO);
      logger.info("Quiz with id " + quizDTO.getQuizId() + " updated successfully.");
      return response;
    } catch (Exception e) {
      logger.severe("An error occurred while updating quiz with name " + quizDTO.getQuizName() + ": " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }


  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzesByTag(Long tagId) {
    try {

      // Get all public quizzes from the database
      Iterable<QuizDAO> quizzes = quizRepository.findAllByIsPublicAndTagDAOs_TagId(true, tagId);

      // Convert quizzes to DTOs
      if (quizzes != null) {
        List<QuizDTO> quizDTOs = new ArrayList<>();
        for (QuizDAO quizDAO : quizzes) {
          quizDTOs.add(quizMapper.toQuizDTO(quizDAO));
        }

        int numQuizzes = quizDTOs.size();
        logger.info(numQuizzes + " public quizzes found. Returning quizzes.");
        return ResponseEntity.ok(quizDTOs);
      } else {
        logger.info("No public quizzes found.");
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe("An error occurred while getting all public quizzes: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzesBySearchString(String searchString) {
    try {
      // Get all public quizzes from the database
      Iterable<QuizDAO> quizzes = quizRepository.findAllPublicQuizzesWithSearchString(searchString.toLowerCase());

      // Convert quizzes to DTOs
      if (quizzes != null) {
        List<QuizDTO> quizDTOs = new ArrayList<>();
        for (QuizDAO quizDAO : quizzes) {
          quizDTOs.add(quizMapper.toQuizDTO(quizDAO));
        }

        int numQuizzes = quizDTOs.size();
        logger.info(numQuizzes + " public quizzes found. Returning quizzes.");
        return ResponseEntity.ok(quizDTOs);
      } else {
        logger.info("No public quizzes found.");
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe("An error occurred while getting all public quizzes: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }

  }
}
