package edu.ntnu.dto.questions;

import org.springframework.lang.Nullable;

/**
 * Data transfer object for the Question entity.
 */
public abstract class QuestionDTO {
  private Long questionId;
  private String questionText;
  private Long quizId;
  private String type;
  @Nullable
  private String imageBase64;

  /**
   * Constructor for the QuestionDTO class.
   */
  public QuestionDTO() {
  }

  /**
   * Constructor for the QuestionDTO class.
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param type the type of the question
   */
  public QuestionDTO(Long questionId, String questionText, Long quizId, String type) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.quizId = quizId;
    this.type = type;
  }

  /**
   * Getter for the question id.
   * @return the question id
   */
  public Long getQuestionId() {
    return questionId;
  }

  /**
   * Setter for the question id.
   * @param questionId the question id
   */
  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  /**
   * Getter for the question text.
   * @return the question text
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * Setter for the question text.
   * @param questionText the question text
   */
  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  /**
   * Getter for the quiz id.
   * @return the quiz id
   */
  public Long getQuizId() {
    return quizId;
  }

  /**
   * Setter for the quiz id.
   * @param quizId the quiz id
   */
  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }
  public String getImageBase64() {
    return imageBase64;
  }
  public void setImageBase64(String imageBase64) {
    this.imageBase64 = imageBase64;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns a string representation of the object.
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "QuestionDTO{" +
        "questionId=" + questionId +
        ", questionText='" + questionText + '\'' +
        ", quizId=" + quizId +
        '}';
  }

}
