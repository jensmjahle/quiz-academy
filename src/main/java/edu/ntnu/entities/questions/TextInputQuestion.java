package edu.ntnu.entities.questions;

import edu.ntnu.entities.Question;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "textInputQuestions")
public class TextInputQuestion extends Question {
  @Column(nullable = false)
  private String question;
  @Column(nullable = false)
  private String answer;

  public TextInputQuestion() {
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
