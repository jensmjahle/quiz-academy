package edu.ntnu.dao.questions;

import jakarta.persistence.Entity;

/**
 * Represents a true/false question in the system.
 */
@Entity
public class TrueFalseQuestionDAO extends QuestionDAO {

  private boolean correctAnswer;

  /**
   * Creates a new instance of the TrueFalseQuestionDAO class.
   */
  public TrueFalseQuestionDAO() {
  }

  /**
   * Creates a new instance of the TrueFalseQuestionDAO class.
   *
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param correctAnswer the correct answer of the question
   */
  public TrueFalseQuestionDAO(Long questionId, String questionText, Long quizId, boolean correctAnswer) {
    super(questionId, questionText, quizId);
    this.correctAnswer = correctAnswer;
  }

  /**
   * Returns the correct answer of the question.
   *
   * @return the correct answer of the question
   */
  public boolean isCorrectAnswer() {
    return correctAnswer;
  }

  /**
   * Sets the correct answer of the question.
   *
   * @param correctAnswer the correct answer of the question
   */
  public void setCorrectAnswer(boolean correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

}
