package edu.ntnu.model.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a question where the user can input a text answer.
 * This is a subclass of the Question class.
 */
@Entity
@Table(name = "textInputQuestions")
public class TextInputQuestion extends Question {

  @Column(nullable = false)
  private String answer;

  public TextInputQuestion() {
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
