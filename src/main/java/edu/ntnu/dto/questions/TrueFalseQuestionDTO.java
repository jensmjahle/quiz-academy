package edu.ntnu.dto.questions;

/**
 * Data transfer object for the TrueFalseQuestion entity.
 */
public class TrueFalseQuestionDTO extends QuestionDTO {
  private boolean correctAnswer;

  /**
   * Constructor for the TrueFalseQuestionDTO class.
   */
  public TrueFalseQuestionDTO() {
  }

  /**
   * Constructor for the TrueFalseQuestionDTO class.
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param type the type of the question
   * @param correctAnswer the correct answer of the question
   */
  public TrueFalseQuestionDTO(Long questionId, String questionText, Long quizId, String type, boolean correctAnswer) {
    super(questionId, questionText, quizId, type);
    this.correctAnswer = correctAnswer;
  }

  /**
   * Getter for the correct answer.
   * @return the correct answer
   */
  public boolean isCorrectAnswer() {
    return correctAnswer;
  }

  /**
   * Setter for the correct answer.
   * @param correctAnswer the correct answer
   */
  public void setCorrectAnswer(boolean correctAnswer) {
    this.correctAnswer = correctAnswer;
  }


}
