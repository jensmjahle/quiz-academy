package edu.ntnu.service;

import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.Question;
import edu.ntnu.model.questions.TextInputQuestion;
import edu.ntnu.repository.questions.MultipleChoiceQuestionRepository;
import edu.ntnu.repository.questions.QuestionRepository;
import edu.ntnu.repository.questions.TextInputQuestionRepository;
import edu.ntnu.utils.QuestionTypeIdentifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
  private final TextInputQuestionRepository textInputQuestionRepository;
  Logger logger = Logger.getLogger(QuestionService.class.getName());
  static ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public QuestionService(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository, TextInputQuestionRepository textInputQuestionRepository) {
    this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
    this.textInputQuestionRepository = textInputQuestionRepository;
  }

  public void deleteMultipleChoiceQuestion(Long questionId) {
    try {
      MultipleChoiceQuestion multipleChoiceQuestion = multipleChoiceQuestionRepository.findByQuestionId(questionId);
      multipleChoiceQuestionRepository.delete(multipleChoiceQuestion);
      logger.info("Multiple choice question with id " + questionId + " deleted.");
    } catch (Exception e) {
      logger.severe("An error occurred while deleting multiple choice question with id " + questionId + ": " + e.getMessage());
      throw e;
    }
  }

  public void deleteTextInputQuestion(Long questionId) {
    try {
      TextInputQuestion textInputQuestion = textInputQuestionRepository.findByQuestionId(questionId);
      textInputQuestionRepository.delete(textInputQuestion);
      logger.info("Text input question with id " + questionId + " deleted.");
    } catch (Exception e) {
      logger.severe("An error occurred while deleting text input question with id " + questionId + ": " + e.getMessage());
      throw e;
    }
  }

  public static MultipleChoiceQuestionDTO convertToMultipleChoiceQuestionDTO(
      MultipleChoiceQuestion multipleChoiceQuestion) {

    return new MultipleChoiceQuestionDTO(
        multipleChoiceQuestion.getQuestionId(),
        multipleChoiceQuestion.getQuestionText(),
        multipleChoiceQuestion.getQuizId(),
        splitStringToList(multipleChoiceQuestion.getAlternatives()),
        splitStringToList(multipleChoiceQuestion.getCorrectAlternatives())
    );
  }

  public static TextInputQuestionDTO convertToTextInputQuestionDTO(
      TextInputQuestion textInputQuestion) {

    return new TextInputQuestionDTO(
        textInputQuestion.getQuestionId(),
        textInputQuestion.getQuestionText(),
        textInputQuestion.getQuizId(),
        splitStringToList(textInputQuestion.getAnswer())
    );
  }

  public static MultipleChoiceQuestion convertToMultipleChoiceQuestion(
      MultipleChoiceQuestionDTO multipleChoiceQuestionDTO) {


    MultipleChoiceQuestion question = new MultipleChoiceQuestion();
    question.setQuestionText(multipleChoiceQuestionDTO.getQuestionText());
    question.setQuizId(multipleChoiceQuestionDTO.getQuizId());
    question.setAlternatives(joinListToString(multipleChoiceQuestionDTO.getAlternatives()));
    question.setCorrectAlternatives(joinListToString(multipleChoiceQuestionDTO.getCorrectAlternatives()));
    return question;
  }

  public static TextInputQuestion convertToTextInputQuestion(
      TextInputQuestionDTO textInputQuestionDTO) {

    TextInputQuestion question = new TextInputQuestion();
    question.setQuestionText(textInputQuestionDTO.getQuestionText());
    question.setQuizId(textInputQuestionDTO.getQuizId());
    question.setAnswer(joinListToString(textInputQuestionDTO.getAnswers()));
    return question;

  }

  public void saveMultipleChoiceQuestion(MultipleChoiceQuestionDTO questionDTO) {
    MultipleChoiceQuestion question = convertToMultipleChoiceQuestion(questionDTO);
    multipleChoiceQuestionRepository.save(question);
    logger.info("Multiple choice question saved successfully.");
  }

  public void saveTextInputQuestion(TextInputQuestionDTO questionDTO) {
    TextInputQuestion question = convertToTextInputQuestion(questionDTO);
    textInputQuestionRepository.save(question);
    logger.info("Text input question saved successfully.");
  }

  private static String joinListToString(List<String> list) {
    return String.join("*", list);
  }

  /**
   * Splits a string formatted like "cow*dog*cat" into a list of strings like cow,dog,cat.
   *
   * @param input String to split (example "cow*dog*cat")
   *
   * @return A list of string
   */
  private static List<String> splitStringToList(String input) {
    String[] parts = input.split("\\*");

    return Arrays.asList(parts);
  }

  public ResponseEntity<QuestionDTO> getQuestion(Long questionId) {
    MultipleChoiceQuestion multipleChoiceQuestion = multipleChoiceQuestionRepository.findByQuestionId(questionId);
    TextInputQuestion textInputQuestion = textInputQuestionRepository.findByQuestionId(questionId);

    // Check if the question was found in the multipleChoiceQuestion table
    if (multipleChoiceQuestion != null) {
      return ResponseEntity.ok(convertToMultipleChoiceQuestionDTO(multipleChoiceQuestion));
    }
    // Check if the question was found in the textInputQuestion table
    else if (textInputQuestion != null) {
      return ResponseEntity.ok(convertToTextInputQuestionDTO(textInputQuestion));
    }
    else {
      return ResponseEntity.notFound().build();
    }

  }

  public ResponseEntity<QuestionDTO> createQuestion(QuestionDTO questionDTO) {
    try {
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(questionDTO);

      switch (questionType) {
        case MULTIPLE_CHOICE:
          saveMultipleChoiceQuestion((MultipleChoiceQuestionDTO) questionDTO);
          break;
        case TEXT_INPUT:
          saveTextInputQuestion((TextInputQuestionDTO) questionDTO);
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
      switch (questionType) {
        case "MULTIPLE_CHOICE":
          deleteMultipleChoiceQuestion(questionId);
          break;
        case "TEXT_INPUT":
          deleteTextInputQuestion(questionId);
          break;
        default:
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok("Question deleted successfully.");

    } catch (Exception e) {
      logger.severe("An error occurred while deleting question with id " + questionId + ": "
          + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  public ResponseEntity<String> updateQuestion(Long questionId, QuestionDTO questionDTO) {
    try {
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(questionDTO);

      switch (questionType) {
        case MULTIPLE_CHOICE:
          MultipleChoiceQuestion oldMCQuestion = multipleChoiceQuestionRepository.findByQuestionId(questionId);
          MultipleChoiceQuestion newMCQuestion = convertToMultipleChoiceQuestion((MultipleChoiceQuestionDTO) questionDTO);
          newMCQuestion.setQuestionId(oldMCQuestion.getQuestionId());

          // Delete the old question and save the new one
          multipleChoiceQuestionRepository.delete(oldMCQuestion);
          multipleChoiceQuestionRepository.save(newMCQuestion);
          break;
        case TEXT_INPUT:
          TextInputQuestion oldTIQuestion = textInputQuestionRepository.findByQuestionId(questionId);
          TextInputQuestion newTIQuestion = convertToTextInputQuestion((TextInputQuestionDTO) questionDTO);
          newTIQuestion.setQuestionId(oldTIQuestion.getQuestionId());

          // Delete the old question and save the new one
          textInputQuestionRepository.delete(oldTIQuestion);
          textInputQuestionRepository.save(newTIQuestion);
          break;
        default:
          return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe("An error occurred while updating question with id " + questionId + ": " + e.getMessage());
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
}
