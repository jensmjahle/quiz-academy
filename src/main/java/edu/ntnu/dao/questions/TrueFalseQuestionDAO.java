package edu.ntnu.dao.questions;

import javax.persistence.Entity;

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
