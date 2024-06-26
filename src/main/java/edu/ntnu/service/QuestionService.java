package edu.ntnu.service;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.mapper.QuestionMapper;
import edu.ntnu.dao.questions.QuestionDAO;
import edu.ntnu.dao.questions.TextInputQuestionDAO;
import edu.ntnu.repository.questions.DragDropQuestionDAORepository;
import edu.ntnu.repository.questions.MultipleChoiceQuestionDAORepository;
import edu.ntnu.repository.questions.TextInputQuestionDAORepository;
import edu.ntnu.repository.questions.TrueFalseQuestionDAORepository;
import edu.ntnu.utils.QuestionTypeIdentifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class for Question objects.
 */
@Service
public class QuestionService {
  private final MultipleChoiceQuestionDAORepository multipleChoiceRepository;
  private final TextInputQuestionDAORepository textInputRepository;
  private final DragDropQuestionDAORepository dragDropRepository;
  private final TrueFalseQuestionDAORepository trueFalseRepository;

  private final QuestionMapper questionMapper;
  private final Logger logger = Logger.getLogger(QuestionService.class.getName());

  /**
   * Constructor for the QuestionService.
   * @param multipleChoiceRepository the repository for MultipleChoiceQuestionDAO objects
   * @param textInputRepository the repository for TextInputQuestionDAO objects
   * @param dragDropRepository the repository for DragDropQuestionDAO objects
   * @param trueFalseRepository the repository for TrueFalseQuestionDAO objects
   * @param questionMapper the mapper for Question objects
   */
  @Autowired
  public QuestionService(
      MultipleChoiceQuestionDAORepository multipleChoiceRepository,
      TextInputQuestionDAORepository textInputRepository,
      DragDropQuestionDAORepository dragDropRepository,
      TrueFalseQuestionDAORepository trueFalseRepository,
      QuestionMapper questionMapper)
  {
    this.multipleChoiceRepository = multipleChoiceRepository;
    this.textInputRepository = textInputRepository;
    this.dragDropRepository = dragDropRepository;
    this.trueFalseRepository = trueFalseRepository;
    this.questionMapper = questionMapper;
  }

  /**
   * Method to get a question by id from the database.
   * If the question is found, the status code is 200.
   * If the question is not found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @param questionId The id of the question to get.
   * @return ResponseEntity with the Question object and status code.
   */
  public ResponseEntity<QuestionDTO> getQuestion(Long questionId) {
    try {
      // Get the question from the database
      QuestionDAO questionDAO = getQuestionById(questionId);

      // Convert the question to a DTO
      QuestionDTO questionDTO = questionMapper.toDTO(questionDAO);

      return ResponseEntity.ok(questionDTO);
    } catch (Exception e) {
      logger.severe("An error occurred while getting question with id " + questionId + ": " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Method to create a question from a QuestionDTO object.
   * Saves the question to the database.
   * If the question is created successfully, the status code is 200.
   * If an error occurs, the status code is 500.
   *
   * @param questionDTO The QuestionDTO object to create the question from.
   * @return ResponseEntity with the Question object and status code.
   */
  public ResponseEntity<QuestionDTO> createQuestion(QuestionDTO questionDTO) {
    try {
      // Identify the question type
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(questionDTO);

      // Convert the DTO to a DAO. The DAO will not have an id, as it is a new question
      QuestionDAO questionDAO = questionMapper.toDAOWithoutId(questionDTO);

      // Save the question to the correct repository
      switch (questionType) {
        case MULTIPLE_CHOICE:
          multipleChoiceRepository.save((MultipleChoiceQuestionDAO) questionDAO);
          break;
        case TEXT_INPUT:
          textInputRepository.save((TextInputQuestionDAO) questionDAO);
          break;
        case DRAG_AND_DROP:
          dragDropRepository.save((DragDropQuestionDAO) questionDAO);
          break;
        case TRUE_FALSE:
          trueFalseRepository.save((TrueFalseQuestionDAO) questionDAO);
          break;
        default:
          return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(questionDTO);
    } catch (Exception e) {
      logger.severe("An error occurred while creating question: " + e.getMessage());
      return ResponseEntity.status(500).build();
  }
}

  /**
   * Method to delete a question by id from the database.
   * If the question is deleted successfully, the status code is 200.
   * If an error occurs, the status code is 500.
   *
   * @param questionId The id of the question to delete.
   * @return ResponseEntity with a message and status code.
   */
  public ResponseEntity<String> deleteQuestion(Long questionId) {
    try {
      // Get the question from the database
      QuestionDAO questionDAO = getQuestionById(questionId);

      // Identify the question type
      QuestionType qType = QuestionTypeIdentifier.identifyQuestionType(questionDAO);


      switch (qType) {
        case MULTIPLE_CHOICE:
          multipleChoiceRepository.delete((MultipleChoiceQuestionDAO) questionDAO);
          break;
        case TEXT_INPUT:
          textInputRepository.delete((TextInputQuestionDAO) questionDAO);
          break;
        case DRAG_AND_DROP:
          dragDropRepository.delete((DragDropQuestionDAO) questionDAO);
          break;
        case TRUE_FALSE:
          trueFalseRepository.delete((TrueFalseQuestionDAO) questionDAO);
          break;
        default:
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok("Question with id \"" + questionId + "\" deleted successfully.");
    } catch (Exception e) {
      logger.severe("An error occurred while deleting question with id " + questionId + ": "
          + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Method to update a question from a QuestionDTO object.
   * Updates the question in the database.
   * If the question is updated successfully, the status code is 200.
   * If an error occurs, the status code is 500.
   *
   * @param newDTO The QuestionDTO object to update the question from.
   * @return ResponseEntity with a message and status code.
   */
  public ResponseEntity<String> updateQuestion(QuestionDTO newDTO) {
    try {
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(newDTO);

      switch (questionType) {
        case MULTIPLE_CHOICE:
          MultipleChoiceQuestionDAO oldMCQuestion = multipleChoiceRepository.findByQuestionId(newDTO.getQuestionId());
          MultipleChoiceQuestionDAO newMCQuestion = (MultipleChoiceQuestionDAO) questionMapper.toDAO(newDTO);

          // Delete the old question and save the new one
          multipleChoiceRepository.delete(oldMCQuestion);
          multipleChoiceRepository.save(newMCQuestion);
          break;
        case TEXT_INPUT:
          TextInputQuestionDAO oldTIQuestion = textInputRepository.findByQuestionId(newDTO.getQuestionId());
          TextInputQuestionDAO newTIQuestion = (TextInputQuestionDAO) questionMapper.toDAO(newDTO);

          // Delete the old question and save the new one
          textInputRepository.delete(oldTIQuestion);
          textInputRepository.save(newTIQuestion);
          break;
        case DRAG_AND_DROP:
          DragDropQuestionDAO oldDDQuestion = dragDropRepository.findByQuestionId(newDTO.getQuestionId());
          DragDropQuestionDAO newDDQuestion = (DragDropQuestionDAO) questionMapper.toDAO(newDTO);

          // Delete the old question and save the new one
          dragDropRepository.delete(oldDDQuestion);
          dragDropRepository.save(newDDQuestion);
          break;
        case TRUE_FALSE:
          TrueFalseQuestionDAO oldTFQuestion = trueFalseRepository.findByQuestionId(newDTO.getQuestionId());
          TrueFalseQuestionDAO newTFQuestion = (TrueFalseQuestionDAO) questionMapper.toDAO(newDTO);

          // Delete the old question and save the new one
          trueFalseRepository.delete(oldTFQuestion);
          trueFalseRepository.save(newTFQuestion);
          break;
        default:
          return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      if (e instanceof DataAccessException) {
        logger.severe("Question with id " + newDTO.getQuestionId() + " not found in database. Cannot update.");
        return ResponseEntity.notFound().build();
      }
      logger.severe("An error occurred while updating question with id " + newDTO.getQuestionId() + ": " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
    return ResponseEntity.ok("Question updated successfully.");
  }

  /**
   * Method to get all questions from the database.
   * If the questions are found, the status code is 200.
   * If the questions are not found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @param quizId The id of the quiz to get the questions from.
   * @return ResponseEntity with the Question objects and status code.
   */
  public  Iterable<QuestionDAO> getAllQuestions(Long quizId) {
    // Get all questions of each type

    Iterable<MultipleChoiceQuestionDAO> multipleChoice = multipleChoiceRepository.findAllByQuizDAO_QuizId(quizId);
    Iterable<TextInputQuestionDAO> textInput = textInputRepository.findAllByQuizDAO_QuizId(quizId);
    Iterable<DragDropQuestionDAO> dragDrop = dragDropRepository.findAllByQuizDAO_QuizId(quizId);
    Iterable<TrueFalseQuestionDAO> trueFalse = trueFalseRepository.findAllByQuizDAO_QuizId(quizId);

    // Combine all questions into one list
    List<QuestionDAO> combinedQuestionDAOS = new ArrayList<>();
    multipleChoice.forEach(combinedQuestionDAOS::add);
    textInput.forEach(combinedQuestionDAOS::add);
    dragDrop.forEach(combinedQuestionDAOS::add);
    trueFalse.forEach(combinedQuestionDAOS::add);

    return combinedQuestionDAOS;

  }

  /**
   * Method to get a question by id from the database.
   * If the question is found, the status code is 200.
   * If the question is not found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @param questionId The id of the question to get.
   * @return ResponseEntity with the Question object and status code.
   */
  public QuestionDAO getQuestionById(Long questionId) {
    QuestionDAO questionDAO = null;

    // Try to find the question in each repository
    try {
      questionDAO = multipleChoiceRepository.findByQuestionId(questionId);
    } catch (Exception e) {
     logger.info("Question with id " + questionId + " not found in multiple choice questions");
    }
    try {
      questionDAO = textInputRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in text input questions");
    }
    try {
      questionDAO = dragDropRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in drag drop questions");
    }
    try {
      questionDAO = trueFalseRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in true false questions");
    }

    // If the question is still null, it was not found in any repository
    if (questionDAO == null) {
      logger.warning("Question with id " + questionId + " not found");
      throw new DataAccessException("Question with id " + questionId + " not found in database") {
      };
    } else {
      return questionDAO;
    }
  }
}
