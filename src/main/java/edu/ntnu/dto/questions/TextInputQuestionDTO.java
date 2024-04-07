package edu.ntnu.dto.questions;

import java.util.List;

/**
 * Data transfer object for the TextInputQuestion entity.
 */
public class TextInputQuestionDTO extends QuestionDTO {
  private List<String> answers;

  /**
   * Constructor for the TextInputQuestionDTO class.
   */
  public TextInputQuestionDTO() {
  }

  /**
   * Constructor for the TextInputQuestionDTO class.
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param type the type of the question
   * @param answers the answers of the question
   */
  public TextInputQuestionDTO(Long questionId, String questionText, Long quizId, String type, List<String> answers) {
    super(questionId, questionText, quizId, type);
    this.answers = answers;
  }

  /**
   * Getter for the answers.
   * @return the answers
   */
  public List<String> getAnswers() {
    return answers;
  }

  /**
   * Setter for the answers.
   * @param answers the answers
   */
  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }
}
