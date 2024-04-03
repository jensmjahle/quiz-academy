package edu.ntnu.dto.questions;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public class DragDropQuestionDTO extends QuestionDTO {
  private Map<String, List<String>> categories;

  public DragDropQuestionDTO() {
  }

  public DragDropQuestionDTO(Long questionId, String questionText, Long quizId, Map<String, List<String>> categories) {
    super(questionId, questionText, quizId);
    this.categories = categories;
  }

  public Map<String, List<String>> getCategories() {
    return categories;
  }

  public void setCategories(Map<String, List<String>> categories) {
    this.categories = categories;
  }
  public List<String> getCategory(String category) {
    return categories.get(category);
  }
  public void setCategory(String category, List<String> values) {
    categories.put(category, values);
  }
  public void addCategory(String category, List<String> values) {
    categories.put(category, values);
  }
  public void removeCategory(String category) {
    categories.remove(category);
  }
  public void clearCategories() {
    categories.clear();
  }
  public List<String> getCategoryNames() {
    return new ArrayList<>(categories.keySet());
  }
}

