package edu.ntnu.dto.questions;

public class TrueFalseQuestionDTO extends QuestionDTO {
  private boolean correctAnswer;

  public TrueFalseQuestionDTO() {
  }

  public TrueFalseQuestionDTO(Long questionId, String questionText, Long quizId, String type, boolean correctAnswer) {
    super(questionId, questionText, quizId, type);
    this.correctAnswer = correctAnswer;
  }

  public boolean isCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(boolean correctAnswer) {
    this.correctAnswer = correctAnswer;
  }


}
