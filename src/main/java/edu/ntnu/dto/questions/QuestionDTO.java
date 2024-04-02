package edu.ntnu.dto.questions;

public abstract class QuestionDTO {
  private Long questionId;
  private String questionText;
  private Long quizId;

  public QuestionDTO() {
  }

  public QuestionDTO(Long questionId, String questionText, Long quizId) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.quizId = quizId;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  @Override
  public String toString() {
    return "QuestionDTO{" +
        "questionId=" + questionId +
        ", questionText='" + questionText + '\'' +
        ", quizId=" + quizId +
        '}';
  }

}
