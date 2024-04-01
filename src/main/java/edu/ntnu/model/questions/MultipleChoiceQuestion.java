package edu.ntnu.model.questions;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Represents a multiple choice question.
 * This is a subclass of the Question class.
 */
@Entity
public class MultipleChoiceQuestion extends Question {

  //alternatives seperated by '*': "dog*cat*milk"
  @Column(nullable = false)
  private String alternatives;

  //correct alternatives seperated by '*': "cat*feline*kitty*pussycat"
  @Column(nullable = false)
  private String correctAlternatives;

  public MultipleChoiceQuestion() {
  }


  public String getAlternatives() {
    return alternatives;
  }

  public void setAlternatives(String alternatives) {
    this.alternatives = alternatives;
  }

  public String getCorrectAlternatives() {
    return correctAlternatives;
  }


  public void setCorrectAlternatives(String newCorrectAlternative) {
    this.correctAlternatives = newCorrectAlternative;
  }

}
