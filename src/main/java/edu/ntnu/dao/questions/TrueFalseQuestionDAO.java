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
@Entity
public class TrueFalseQuestionDAO extends QuestionDAO {

  private boolean correctAnswer;

  public TrueFalseQuestionDAO() {
  }

  public TrueFalseQuestionDAO(Long questionId, String questionText, Long quizId, boolean correctAnswer) {
    super(questionId, questionText, quizId);
    this.correctAnswer = correctAnswer;
  }

  public boolean isCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(boolean correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

}
