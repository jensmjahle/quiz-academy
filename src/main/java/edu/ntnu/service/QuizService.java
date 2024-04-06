package edu.ntnu.service;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.mapper.QuizMapper;
import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.questions.TextInputQuestionDAO;
import edu.ntnu.repository.QuizRepository;
import edu.ntnu.repository.questions.DragDropQuestionDAORepository;
import edu.ntnu.repository.questions.MultipleChoiceQuestionDAORepository;
import edu.ntnu.repository.questions.TextInputQuestionDAORepository;
import edu.ntnu.repository.questions.TrueFalseQuestionDAORepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
/*
  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzesByTag(String tag) {
  }

 */
}
