package edu.ntnu.dto.questions;

import org.springframework.lang.Nullable;

public abstract class QuestionDTO {
  private Long questionId;
  private String questionText;
  private Long quizId;
  @Nullable
  private String imageBase64;

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
  public String getImageBase64() {
    return imageBase64;
  }
  public void setImageBase64(String imageBase64) {
    this.imageBase64 = imageBase64;
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
