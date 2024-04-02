package edu.ntnu.service;

import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.mapper.QuestionMapper;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.Question;
import edu.ntnu.model.questions.TextInputQuestion;
import edu.ntnu.repository.questions.MultipleChoiceQuestionRepository;
import edu.ntnu.repository.questions.TextInputQuestionRepository;
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
  private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
  private final TextInputQuestionRepository textInputQuestionRepository;
  private final QuestionMapper questionMapper;
  private final Logger logger = Logger.getLogger(QuestionService.class.getName());

  @Autowired
  public QuestionService(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository, TextInputQuestionRepository textInputQuestionRepository, QuestionMapper questionMapper) {
    this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
    this.textInputQuestionRepository = textInputQuestionRepository;
    this.questionMapper = questionMapper;
  }


  public ResponseEntity<QuestionDTO> getQuestion(Long questionId) {
    try {
      // Get the question from the database
      Question question = getQuestionById(questionId);

      // Convert the question to a DTO
      QuestionDTO questionDTO = questionMapper.toQuestionDTO(question);

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

      // Convert the DTO to a model
      Question question = questionMapper.toQuestion(questionDTO);

      // Save the question to the correct repository
      switch (questionType) {
        case MULTIPLE_CHOICE:
          multipleChoiceQuestionRepository.save((MultipleChoiceQuestion) question);
          break;
        case TEXT_INPUT:
          textInputQuestionRepository.save((TextInputQuestion) question);
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

  public ResponseEntity<String> deleteQuestion(Long questionId, String questionType) {
    try {
      // Identify the question type
      QuestionType qType = QuestionTypeIdentifier.indentifyQuestionTypeFromString(questionType);

      // Get the question from the database
      Question question = getQuestionById(questionId);

      switch (qType) {
        case MULTIPLE_CHOICE:
          multipleChoiceQuestionRepository.delete(question);
          break;
        case TEXT_INPUT:
          textInputQuestionRepository.delete(question);
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

  public ResponseEntity<String> updateQuestion(QuestionDTO newQuestionDTO) {
    try {
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(newQuestionDTO);

      switch (questionType) {
        case MULTIPLE_CHOICE:
          MultipleChoiceQuestion oldMCQuestion = multipleChoiceQuestionRepository.findByQuestionId(newQuestionDTO.getQuestionId());
          MultipleChoiceQuestion newMCQuestion = (MultipleChoiceQuestion) questionMapper.toQuestion(newQuestionDTO);
          newMCQuestion.setQuestionId(oldMCQuestion.getQuestionId());

          // Delete the old question and save the new one
          multipleChoiceQuestionRepository.delete(oldMCQuestion);
          multipleChoiceQuestionRepository.save(newMCQuestion);
          break;
        case TEXT_INPUT:
          TextInputQuestion oldTIQuestion = textInputQuestionRepository.findByQuestionId(newQuestionDTO.getQuestionId());
          TextInputQuestion newTIQuestion = (TextInputQuestion) questionMapper.toQuestion(newQuestionDTO);
          newTIQuestion.setQuestionId(oldTIQuestion.getQuestionId());

          // Delete the old question and save the new one
          textInputQuestionRepository.delete(oldTIQuestion);
          textInputQuestionRepository.save(newTIQuestion);
          break;
        default:
          return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      if (e instanceof DataAccessException) {
        logger.severe("Question with id " + newQuestionDTO.getQuestionId() + " not found in database. Cannot update.");
        return ResponseEntity.notFound().build();
      }
      logger.severe("An error occurred while updating question with id " + newQuestionDTO.getQuestionId() + ": " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
    return ResponseEntity.ok("Question updated successfully.");
  }

  public  Iterable<Question> getAllQuestions(Long quizId) {
    Iterable<MultipleChoiceQuestion> multipleChoiceQuestions = multipleChoiceQuestionRepository.findAllByQuiz_QuizId(quizId);
    Iterable<TextInputQuestion> textInputQuestions = textInputQuestionRepository.findAllByQuiz_QuizId(quizId);

    // Combine all questions into one list
    List<Question> combinedQuestions = new ArrayList<>();
    multipleChoiceQuestions.forEach(combinedQuestions::add);
    textInputQuestions.forEach(combinedQuestions::add);

    return combinedQuestions;
  }

  public Question getQuestionById(Long questionId) {
    Question question = null;
    try {
      question = multipleChoiceQuestionRepository.findByQuestionId(questionId);
    } catch (Exception e) {
     logger.info("Question with id " + questionId + " not found in multiple choice questions");
    }
    try {
      question = textInputQuestionRepository.findByQuestionId(questionId);
    } catch (Exception e) {
      logger.info("Question with id " + questionId + " not found in text input questions");
    }

    if (question == null) {
      logger.warning("Question with id " + questionId + " not found");
      throw new DataAccessException("Question with id " + questionId + " not found in database") {
      };
    } else {
      return question;
    }
  }
}
