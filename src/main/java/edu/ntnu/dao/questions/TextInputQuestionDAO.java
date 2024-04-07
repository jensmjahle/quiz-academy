package edu.ntnu.dao.questions;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

/**
 * Represents a question where the user can input a text answer.
 * This is a subclass of the Question class.
 */
@Entity
public class TextInputQuestionDAO extends QuestionDAO {

  @Column(nullable = false)
  private String answer;

  /**
   * Creates a new instance of the TextInputQuestionDAO class.
   */
  public TextInputQuestionDAO() {
  }

  /**
   * Creates a new instance of the TextInputQuestionDAO class.
   *
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param s the answer of the question
   */
  public TextInputQuestionDAO(Long questionId, String questionText, Long quizId, String s) {
    super(questionId, questionText, quizId);
    this.answer = s;
  }

  /**
   * Returns the answer of the question.
   *
   * @return the answer of the question
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Sets the answer of the question.
   *
   * @param answer the answer of the question
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
