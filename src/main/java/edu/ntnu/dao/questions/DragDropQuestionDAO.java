package edu.ntnu.dao.questions;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

/**
 * Represents a drag and drop question in the system.
 * This is a subclass of the Question class.
 */
@Entity
public class DragDropQuestionDAO extends QuestionDAO {

  //CategoryName seperated from items by '<'
  //CategoryItem seperated by '*': "cat*dog*bird"
  //Categories seperated by ';'
  //Example: "Animals<cat*dog*bird;Fruits<apple*banana*orange"
  @Column(nullable = false)
  private String categories;

  /**
   * Creates a new instance of the DragDropQuestionDAO class.
   */
  public DragDropQuestionDAO() {
  }

  /**
   * Creates a new instance of the DragDropQuestionDAO class.
   *
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param categories the categories of the question
   */
  public DragDropQuestionDAO(Long questionId, String questionText, Long quizId, String categories) {
    super(questionId, questionText, quizId);
    this.categories = categories;
  }

  /**
   * Returns the categories of the question.
   *
   * @return the categories of the question
   */
  public String getCategories() {
    return categories;
  }

  /**
   * Sets the categories of the question.
   *
   * @param categories the categories of the question
   */
  public void setCategories(String categories) {
    this.categories = categories;
  }
}
