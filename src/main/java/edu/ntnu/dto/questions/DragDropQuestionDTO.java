package edu.ntnu.dto.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data transfer object for the DragDropQuestion entity.
 */
public class DragDropQuestionDTO extends QuestionDTO {
  private Map<String, List<String>> categories;

  /**
   * Constructor for the DragDropQuestionDTO class.
   */
  public DragDropQuestionDTO() {
  }

  /**
   * Constructor for the DragDropQuestionDTO class.
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param type the type of the question
   * @param categories the categories of the question
   */
  public DragDropQuestionDTO(Long questionId, String questionText, Long quizId, String type, Map<String, List<String>> categories) {
    super(questionId, questionText, quizId, type);
    this.categories = categories;
  }

  /**
   * Getter for the categories.
   * @return the categories
   */
  public Map<String, List<String>> getCategories() {
    return categories;
  }

  /**
   * Setter for the categories.
   * @param categories the categories
   */
  public void setCategories(Map<String, List<String>> categories) {
    this.categories = categories;
  }

  /**
   * Getter for the values of a category.
   * @param category the category
   * @return the values of the category
   */
  public List<String> getCategory(String category) {
    return categories.get(category);
  }

  /**
   * Setter for the values of a category.
   * @param category the category
   * @param values the values
   */
  public void setCategory(String category, List<String> values) {
    categories.put(category, values);
  }

  /**
   * Add a category.
   * @param category the category
   * @param values the values
   */
  public void addCategory(String category, List<String> values) {
    categories.put(category, values);
  }

  /**
   * Remove a category.
   * @param category the category
   */
  public void removeCategory(String category) {
    categories.remove(category);
  }

  /**
   * Clear all categories.
   */
  public void clearCategories() {
    categories.clear();
  }

  /**
   * Get the names of the categories.
   * @return the names of the categories
   */
  public List<String> getCategoryNames() {
    return new ArrayList<>(categories.keySet());
  }
}

