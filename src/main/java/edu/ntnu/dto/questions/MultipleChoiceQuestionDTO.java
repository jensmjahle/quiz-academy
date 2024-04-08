package edu.ntnu.dto.questions;

import java.util.List;

/**
 * Data transfer object for the MultipleChoiceQuestion entity.
 */
public class MultipleChoiceQuestionDTO extends QuestionDTO {
  private List<String> alternatives;
  private List<String> correctAlternatives;

  /**
   * Constructor for the MultipleChoiceQuestionDTO class.
   */
  public MultipleChoiceQuestionDTO() {
  }

  /**
   * Constructor for the MultipleChoiceQuestionDTO class.
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param type the type of the question
   * @param alternatives the alternatives of the question
   * @param correctAlternatives the correct alternatives of the question
   */
  public MultipleChoiceQuestionDTO(Long questionId, String questionText, Long quizId, String type, List<String> alternatives, List<String> correctAlternatives) {
    super(questionId, questionText, quizId, type);
    this.alternatives = alternatives;
    this.correctAlternatives = correctAlternatives;
  }

  /**
   * Getter for the alternatives.
   * @return the alternatives
   */
  public List<String> getAlternatives() {
    return alternatives;
  }

  /**
   * Setter for the alternatives.
   * @param alternatives the alternatives
   */
  public void setAlternatives(List<String> alternatives) {
    this.alternatives = alternatives;
  }

  /**
   * Getter for the correct alternatives.
   * @return the correct alternatives
   */
  public List<String> getCorrectAlternatives() {
    return correctAlternatives;
  }

  /**
   * Setter for the correct alternatives.
   * @param correctAlternatives the correct alternatives
   */
  public void setCorrectAlternatives(List<String> correctAlternatives) {
    this.correctAlternatives = correctAlternatives;
  }
}
