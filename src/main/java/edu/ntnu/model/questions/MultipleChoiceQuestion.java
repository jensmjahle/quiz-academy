package edu.ntnu.model.questions;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Represents a multiple choice question.
 * This is a subclass of the Question class.
 */
@Entity
public class MultipleChoiceQuestion extends Question {

  @Column(nullable = false)
  private String alternative1;
  private String alternative2;
  private String alternative3;
  private String alternative4;
  @Column(nullable = false)
  private String correctAlternative;

  public MultipleChoiceQuestion() {
  }


  public String getCorrectAlternative() {
    return correctAlternative;
  }


  public void setCorrectAlternative(String newCorrectAlternative) {
    this.correctAlternative = newCorrectAlternative;
  }
}
