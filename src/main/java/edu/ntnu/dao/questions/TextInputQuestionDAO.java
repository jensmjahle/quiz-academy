package edu.ntnu.dao.questions;


import edu.ntnu.dao.QuizDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
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
