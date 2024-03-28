package edu.ntnu.entities.questions;

import edu.ntnu.entities.Question;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class MultipleChoiceQuestion extends Question {

  @Column(nullable = false)
  private String answerAlternatives;
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
