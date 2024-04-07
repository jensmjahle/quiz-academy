package edu.ntnu.dao.questions;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

/**
 * Represents a multiple choice question.
 * This is a subclass of the Question class.
 */
@Entity
public class MultipleChoiceQuestionDAO extends QuestionDAO {

  //alternatives seperated by '*': "dog*cat*milk"
  @Column(nullable = false)
  private String alternatives;

  //correct alternatives seperated by '*': "cat*feline*kitty*pussycat"
  @Column(nullable = false)
  private String correctAlternatives;

  /**
   * Creates a new instance of the MultipleChoiceQuestionDAO class.
   */
  public MultipleChoiceQuestionDAO() {
  }

  /**
   * Creates a new instance of the MultipleChoiceQuestionDAO class.
   *
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   * @param s the alternatives of the question
   * @param s1 the correct alternatives of the question
   */
  public MultipleChoiceQuestionDAO(Long questionId, String questionText, Long quizId, String s, String s1) {
super(questionId, questionText, quizId);
    this.alternatives = s;
    this.correctAlternatives = s1;
  }

  /**
   * Returns the alternatives of the question.
   *
   * @return the alternatives of the question
   */
  public String getAlternatives() {
    return alternatives;
  }

  /**
   * Sets the alternatives of the question.
   *
   * @param alternatives the alternatives of the question
   */
  public void setAlternatives(String alternatives) {
    this.alternatives = alternatives;
  }

  /**
   * Returns the correct alternatives of the question.
   *
   * @return the correct alternatives of the question
   */
  public String getCorrectAlternatives() {
    return correctAlternatives;
  }

  /**
   * Sets the correct alternatives of the question.
   *
   * @param newCorrectAlternative the correct alternatives of the question
   */
  public void setCorrectAlternatives(String newCorrectAlternative) {
    this.correctAlternatives = newCorrectAlternative;
  }

}
