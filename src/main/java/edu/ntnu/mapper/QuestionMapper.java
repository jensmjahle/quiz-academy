package edu.ntnu.mapper;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import edu.ntnu.dto.questions.DragDropQuestionDTO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.dto.questions.TrueFalseQuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.dao.questions.QuestionDAO;
import edu.ntnu.dao.questions.TextInputQuestionDAO;
import edu.ntnu.utils.QuestionTypeIdentifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
  private static final Logger logger = Logger.getLogger(QuestionMapper.class.getName());

  /**
   * Maps a QuestionDAO to a QuestionDTO.
   *
   * @param questionDAO The QuestionDAO to map
   *
   * @return The QuestionDTO
   */
    public QuestionDTO toDTO(QuestionDAO questionDAO) {
      Long questionId = questionDAO.getQuestionId();
      String questionText = questionDAO.getQuestionText();
      Long quizId = questionDAO.getQuizId();
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionType(questionDAO);
      String imageBase64 = Base64Mapper.convertImageToBase64(questionDAO.getQuestionImage());

      switch (questionType) {
        case MULTIPLE_CHOICE:
          MultipleChoiceQuestionDTO mcq = new MultipleChoiceQuestionDTO();
          mcq.setQuestionId(questionId);
          mcq.setQuestionText(questionText);
          mcq.setQuizId(quizId);
          mcq.setType(QuestionType.MULTIPLE_CHOICE.toString());
          mcq.setAlternatives(splitStringToList(((MultipleChoiceQuestionDAO) questionDAO).getAlternatives()));
          mcq.setCorrectAlternatives(splitStringToList(((MultipleChoiceQuestionDAO) questionDAO).getCorrectAlternatives()));
          if (imageBase64 != null) { mcq.setImageBase64(imageBase64); }
          return mcq;
        case TEXT_INPUT:
          TextInputQuestionDTO tiq = new TextInputQuestionDTO();
          tiq.setQuestionId(questionId);
          tiq.setQuestionText(questionText);
          tiq.setQuizId(quizId);
          tiq.setType(QuestionType.TEXT_INPUT.toString());
          tiq.setAnswers(splitStringToList(((TextInputQuestionDAO) questionDAO).getAnswer()));
          if (imageBase64 != null) { tiq.setImageBase64(imageBase64); }
          return tiq;
        case DRAG_AND_DROP:
          DragDropQuestionDTO ddq = new DragDropQuestionDTO();
          ddq.setQuestionId(questionId);
          ddq.setQuestionText(questionText);
          ddq.setQuizId(quizId);
          ddq.setType(QuestionType.DRAG_AND_DROP.toString());
          ddq.setCategories(mapStringToCategories(((DragDropQuestionDAO) questionDAO).getCategories()));
          if (imageBase64 != null) { ddq.setImageBase64(imageBase64); }
          return ddq;
        case TRUE_FALSE:
          TrueFalseQuestionDTO tfq = new TrueFalseQuestionDTO();
          tfq.setQuestionId(questionId);
          tfq.setQuestionText(questionText);
          tfq.setQuizId(quizId);
          tfq.setType(QuestionType.TRUE_FALSE.toString());
          tfq.setCorrectAnswer(((TrueFalseQuestionDAO) questionDAO).isCorrectAnswer());
          if (imageBase64 != null) { tfq.setImageBase64(imageBase64); }
          return tfq;
        default:
          throw new IllegalArgumentException("Unknown question type. Cannot convert to DTO");
      }
    }

  /**
   * Maps a QuestionDTO to a QuestionDAO.
   * Used when updating an existing question.
   *
   * @param questionDTO The QuestionDTO to map
   *
   * @return The QuestionDAO
   */
    public QuestionDAO toDAO(QuestionDTO questionDTO) {
      try {
        QuestionDAO questionDAO = toDAOWithoutId(questionDTO);
        questionDAO.setQuestionId(questionDTO.getQuestionId());
        return questionDAO;

      } catch (Exception e) {
        logger.severe(e.getMessage());
        throw e;
      }


    }

  /**
   * Maps a QuestionDTO to a QuestionDAO without an ID.
   * Used when creating a new question. ID is generated by the database.
   *
   * @param questionDTO The QuestionDTO to map
   *
   * @return The QuestionDAO without an ID
   */
  public QuestionDAO toDAOWithoutId(QuestionDTO questionDTO) {
      String questionText = questionDTO.getQuestionText();
      Long quizId = questionDTO.getQuizId();
      QuestionType questionType = QuestionTypeIdentifier.identifyQuestionDTOType(questionDTO);
      byte[] image = Base64Mapper.convertBase64ToImage(questionDTO.getImageBase64());

      switch (questionType) {
        case MULTIPLE_CHOICE:
          MultipleChoiceQuestionDAO mcq = new MultipleChoiceQuestionDAO();
          mcq.setQuestionText(questionText);
          mcq.setQuizId(quizId);
          mcq.setAlternatives(joinListToString(((MultipleChoiceQuestionDTO) questionDTO).getAlternatives()));
          mcq.setCorrectAlternatives(joinListToString(((MultipleChoiceQuestionDTO) questionDTO).getCorrectAlternatives()));
          if (image != null) { mcq.setQuestionImage(image); }
          return mcq;
        case TEXT_INPUT:
          TextInputQuestionDAO tiq = new TextInputQuestionDAO();
          tiq.setQuestionText(questionText);
          tiq.setQuizId(quizId);
          tiq.setAnswer(joinListToString(((TextInputQuestionDTO) questionDTO).getAnswers()));
          if (image != null) { tiq.setQuestionImage(image); }
          return tiq;
        case DRAG_AND_DROP:
          DragDropQuestionDAO ddq = new DragDropQuestionDAO();
          ddq.setQuestionText(questionText);
          ddq.setQuizId(quizId);
          ddq.setCategories(mapCategoriesToString(((DragDropQuestionDTO) questionDTO).getCategories()));
          if (image != null) { ddq.setQuestionImage(image); }
          return ddq;
        case TRUE_FALSE:
          TrueFalseQuestionDAO tfq = new TrueFalseQuestionDAO();
          tfq.setQuestionText(questionText);
          tfq.setQuizId(quizId);
          tfq.setCorrectAnswer(((TrueFalseQuestionDTO) questionDTO).isCorrectAnswer());
          if (image != null) { tfq.setQuestionImage(image); }
          return tfq;
        default:
          throw new IllegalArgumentException("Unknown question type. Cannot convert to model");
    }
  }

  /**
   * Helper method that
   * Splits a String into a List of Strings separated by "*"
   * Example: "item1*item2*item3" -> ["item1", "item2", "item3"]
   *
   * @param input The String to split
   *
   * @return The List of Strings
   */
  private List<String> splitStringToList(String input) {
    String[] parts = input.split("\\*");
    return Arrays.asList(parts);
  }

  /**
   * Helper method that
   * Joins a List of Strings into a single String separated by "*"
   * Example: ["item1", "item2", "item3"] -> "item1*item2*item3"
   *
   * @param list The List of Strings to join
   *
   * @return The joined String
   */
  private String joinListToString(List<String> list) {
    return String.join("*", list);
  }


  /**
   * Helper method that
   * Maps the categories from the DTO to a String used in the DAO
   *
   * @param categories The categories to map. The key is the category name, and the value is a list of items in the category
   *
   * @return The categories as a String. The format is "categoryName<item1*item2;categoryName2<item1*item2;..."
   */
  private static String mapCategoriesToString(Map<String, List<String>> categories) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, List<String>> entry : categories.entrySet()) {
      String categoryName = entry.getKey();
      List<String> categoryItems = entry.getValue();
      sb.append(categoryName).append("<").append(String.join("*", categoryItems)).append(";");
    }
    return sb.toString();
  }


  /**
   * Helper method that
   * Maps the categories from a String used in the DAO to a Map used in the DTO
   *
   * @param categoriesString The categories as a String. The format is "categoryName<item1*item2;categoryName2<item1*item2;..."
   *
   * @return The categories as a Map. The key is the category name, and the value is a list of items in the category
   */
  private static Map<String, List<String>> mapStringToCategories(String categoriesString) {
    Map<String, List<String>> categories = new HashMap<>();
    String[] categoryEntries = categoriesString.split(";");
    for (String entry : categoryEntries) {
      String[] parts = entry.split("<");
      String categoryName = parts[0];
      String[] items = parts[1].split("\\*");
      List<String> categoryItems = new ArrayList<>(Arrays.asList(items));
      categories.put(categoryName, categoryItems);
    }
    return categories;
  }

}
