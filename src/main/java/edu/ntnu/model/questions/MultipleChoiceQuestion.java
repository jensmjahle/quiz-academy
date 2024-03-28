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

  public void setAlternative1(String s) {
    this.alternative1 = s;
  }

  public String getAlternative1() {
    return alternative1;
  }

  public void setAlternative2(String s) {
    this.alternative2 = s;
  }

  public String getAlternative2() {
    return alternative2;
  }

  public void setAlternative3(String s) {
    this.alternative3 = s;
  }

  public String getAlternative3() {
    return alternative3;
  }

  public void setAlternative4(String s) {
    this.alternative4 = s;
  }

  public String getAlternative4() {
    return alternative4;
  }
}
