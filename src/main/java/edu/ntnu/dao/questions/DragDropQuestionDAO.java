package edu.ntnu.dao.questions;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DragDropQuestionDAO extends QuestionDAO {

  //CategoryName seperated from items by '<'
  //CategoryItem seperated by '*': "cat*dog*bird"
  //Categories seperated by ';'
  //Example: "Animals<cat*dog*bird;Fruits<apple*banana*orange"
  @Column(nullable = false)
  private String categories;

  public DragDropQuestionDAO() {
  }

  public DragDropQuestionDAO(Long questionId, String questionText, Long quizId, String categories) {
    super(questionId, questionText, quizId);
    this.categories = categories;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }
}
