package edu.ntnu.dao.questions;

import edu.ntnu.dao.questions.QuestionDAO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a question where the user can input a text answer.
 * This is a subclass of the Question class.
 */
@Entity
public class TextInputQuestionDAO extends QuestionDAO {

  @Column(nullable = false)
  private String answer;

  public TextInputQuestionDAO() {
  }

  public TextInputQuestionDAO(Long questionId, String questionText, Long quizId, String s) {
    super(questionId, questionText, quizId);
    this.answer = s;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
