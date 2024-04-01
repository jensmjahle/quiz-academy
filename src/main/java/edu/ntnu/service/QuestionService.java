package edu.ntnu.service;

import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.TextInputQuestion;
import edu.ntnu.repository.questions.MultipleChoiceQuestionRepository;
import edu.ntnu.repository.questions.QuestionRepository;
import edu.ntnu.repository.questions.TextInputQuestionRepository;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
  private final TextInputQuestionRepository textInputQuestionRepository;
  Logger logger = Logger.getLogger(QuestionService.class.getName());

  @Autowired
  public QuestionService(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository, TextInputQuestionRepository textInputQuestionRepository) {
    this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
    this.textInputQuestionRepository = textInputQuestionRepository;
  }


  public void deleteMultipleChoiceQuestion(Long questionId) {
    try {
      logger.info("Received request to delete multiple choice question with id: " + questionId);
      multipleChoiceQuestionRepository.deleteById(questionId);
      logger.info("Multiple choice question with id " + questionId + " deleted.");
    } catch (Exception e) {
      logger.severe("An error occurred while deleting multiple choice question with id " + questionId + ": " + e.getMessage());
    }
  }

  public void deleteTextInputQuestion(Long questionId) {
    try {
      logger.info("Received request to delete text input question with id: " + questionId);
      textInputQuestionRepository.deleteById(questionId);
      logger.info("Text input question with id " + questionId + " deleted.");
    } catch (Exception e) {
      logger.severe("An error occurred while deleting text input question with id " + questionId + ": " + e.getMessage());
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

    return new MultipleChoiceQuestion(
        multipleChoiceQuestionDTO.getQuestionId(),
        multipleChoiceQuestionDTO.getQuestionText(),
        multipleChoiceQuestionDTO.getQuizId(),
        joinListToString(multipleChoiceQuestionDTO.getAlternatives()),
        joinListToString(multipleChoiceQuestionDTO.getCorrectAlternatives())
    );
  }

  public static TextInputQuestion convertToTextInputQuestion(
      TextInputQuestionDTO textInputQuestionDTO) {

    return new TextInputQuestion(
        textInputQuestionDTO.getQuestionId(),
        textInputQuestionDTO.getQuestionText(),
        textInputQuestionDTO.getQuizId(),
        joinListToString(textInputQuestionDTO.getAnswers())
    );
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

}
