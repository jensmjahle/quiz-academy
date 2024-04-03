package edu.ntnu.service;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.mapper.QuestionMapper;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.Question;
import edu.ntnu.model.questions.TextInputQuestion;
import edu.ntnu.repository.questions.DragDropQuestionDAORepository;
import edu.ntnu.repository.questions.MultipleChoiceQuestionRepository;
import edu.ntnu.repository.questions.TextInputQuestionRepository;
import edu.ntnu.repository.questions.TrueFalseQuestionDAORepository;
import edu.ntnu.utils.QuestionTypeIdentifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  private final MultipleChoiceQuestionRepository multipleChoiceRepository;
  private final TextInputQuestionRepository textInputRepository;
  private final DragDropQuestionDAORepository dragDropRepository;
  private final TrueFalseQuestionDAORepository trueFalseRepository;

  private final QuestionMapper questionMapper;
  private final Logger logger = Logger.getLogger(QuestionService.class.getName());

  @Autowired
  public QuestionService(
      MultipleChoiceQuestionRepository multipleChoiceRepository,
      TextInputQuestionRepository textInputRepository,
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


  public ResponseEntity<QuestionDTO> getQuestion(Long questionId) {
    try {
      // Get the question from the database
      Question question = getQuestionById(questionId);

      // Convert the question to a DTO
      QuestionDTO questionDTO = questionMapper.toDTO(question);

      return ResponseEntity.ok(questionDTO);
    } catch (Exception e) {
      logger.severe("An error occurred while getting question with id " + questionId + ": " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  public ResponseEntity<QuestionDTO> createQuestion(QuestionDTO questionDTO) {
    try {
      // Identify the question type
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(questionDTO);

      // Convert the DTO to a DAO. The DAO will not have an id, as it is a new question
      Question question = questionMapper.toDAOWithoutId(questionDTO);

      // Save the question to the correct repository
      switch (questionType) {
        case MULTIPLE_CHOICE:
          multipleChoiceRepository.save((MultipleChoiceQuestion) question);
          break;
        case TEXT_INPUT:
          textInputRepository.save((TextInputQuestion) question);
          break;
        case DRAG_AND_DROP:
          dragDropRepository.save((DragDropQuestionDAO) question);
          break;
        case TRUE_FALSE:
          trueFalseRepository.save((TrueFalseQuestionDAO) question);
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

  public ResponseEntity<String> deleteQuestion(Long questionId) {
    try {
      // Get the question from the database
      Question question = getQuestionById(questionId);

      // Identify the question type
      QuestionType qType = QuestionTypeIdentifier.identifyQuestionType(question);


      switch (qType) {
        case MULTIPLE_CHOICE:
          multipleChoiceRepository.delete(question);
          break;
        case TEXT_INPUT:
          textInputRepository.delete(question);
          break;
        case DRAG_AND_DROP:
          dragDropRepository.delete(question);
          break;
        case TRUE_FALSE:
          trueFalseRepository.delete(question);
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

  public ResponseEntity<String> updateQuestion(QuestionDTO newDTO) {
    try {
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(newDTO);

      switch (questionType) {
        case MULTIPLE_CHOICE:
          MultipleChoiceQuestion oldMCQuestion = multipleChoiceRepository.findByQuestionId(newDTO.getQuestionId());
          MultipleChoiceQuestion newMCQuestion = (MultipleChoiceQuestion) questionMapper.toDAO(newDTO);

          // Delete the old question and save the new one
          multipleChoiceRepository.delete(oldMCQuestion);
          multipleChoiceRepository.save(newMCQuestion);
          break;
        case TEXT_INPUT:
          TextInputQuestion oldTIQuestion = textInputRepository.findByQuestionId(newDTO.getQuestionId());
          TextInputQuestion newTIQuestion = (TextInputQuestion) questionMapper.toDAO(newDTO);

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

  public  Iterable<Question> getAllQuestions(Long quizId) {
    // Get all questions of each type
    Iterable<MultipleChoiceQuestion> multipleChoice = multipleChoiceRepository.findAllByQuiz_QuizId(quizId);
    Iterable<TextInputQuestion> textInput = textInputRepository.findAllByQuiz_QuizId(quizId);
    Iterable<DragDropQuestionDAO> dragDrop = dragDropRepository.findAllByQuiz_QuizId(quizId);
    Iterable<TrueFalseQuestionDAO> trueFalse = trueFalseRepository.findAllByQuiz_QuizId(quizId);

    // Combine all questions into one list
    List<Question> combinedQuestions = new ArrayList<>();
    multipleChoice.forEach(combinedQuestions::add);
    textInput.forEach(combinedQuestions::add);
    dragDrop.forEach(combinedQuestions::add);
    trueFalse.forEach(combinedQuestions::add);

    return combinedQuestions;
  }

  public Question getQuestionById(Long questionId) {
    Question question = null;

    // Try to find the question in each repository
    try {
      question = multipleChoiceRepository.findByQuestionId(questionId);
    } catch (Exception e) {
     logger.info("Question with id " + questionId + " not found in multiple choice questions");
    }
    try {
      question = textInputRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in text input questions");
    }
    try {
      question = dragDropRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in drag drop questions");
    }
    try {
      question = trueFalseRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in true false questions");
    }

    // If the question is still null, it was not found in any repository
    if (question == null) {
      logger.warning("Question with id " + questionId + " not found");
      throw new DataAccessException("Question with id " + questionId + " not found in database") {
      };
    } else {
      return question;
    }
  }
}
