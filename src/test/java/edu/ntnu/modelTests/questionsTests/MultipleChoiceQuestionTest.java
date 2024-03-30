package edu.ntnu.modelTests.questionsTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.ntnu.model.questions.MultipleChoiceQuestion;

public class MultipleChoiceQuestionTest {

  @Test
  @DisplayName("MultipleChoiceQuestion is correctly instantiated")
  public void testMultipleChoiceQuestionInstantiation() {
    // Arrange
    String alternative1 = "Option 1";
    String alternative2 = "Option 2";
    String alternative3 = "Option 3";
    String alternative4 = "Option 4";
    String correctAlternative = "Option 1";

    // Act
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setAlternative1(alternative1);
    multipleChoiceQuestion.setAlternative2(alternative2);
    multipleChoiceQuestion.setAlternative3(alternative3);
    multipleChoiceQuestion.setAlternative4(alternative4);
    multipleChoiceQuestion.setCorrectAlternatives(correctAlternative);

    // Assert
    assertThat(multipleChoiceQuestion).isNotNull();
    assertThat(multipleChoiceQuestion.getAlternative1()).isEqualTo(alternative1);
    assertThat(multipleChoiceQuestion.getAlternative2()).isEqualTo(alternative2);
    assertThat(multipleChoiceQuestion.getAlternative3()).isEqualTo(alternative3);
    assertThat(multipleChoiceQuestion.getAlternative4()).isEqualTo(alternative4);
    assertThat(multipleChoiceQuestion.getCorrectAlternatives()).isEqualTo(correctAlternative);
  }

  @Test
  @DisplayName("Getters and setters work correctly")
  public void testGettersAndSetters() {
    // Arrange
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    String alternative1 = "Option 1";
    String alternative2 = "Option 2";
    String alternative3 = "Option 3";
    String alternative4 = "Option 4";
    String correctAlternative = "Option 1";

    // Act
    multipleChoiceQuestion.setAlternative1(alternative1);
    multipleChoiceQuestion.setAlternative2(alternative2);
    multipleChoiceQuestion.setAlternative3(alternative3);
    multipleChoiceQuestion.setAlternative4(alternative4);
    multipleChoiceQuestion.setCorrectAlternatives(correctAlternative);

    // Assert
    assertThat(multipleChoiceQuestion.getAlternative1()).isEqualTo(alternative1);
    assertThat(multipleChoiceQuestion.getAlternative2()).isEqualTo(alternative2);
    assertThat(multipleChoiceQuestion.getAlternative3()).isEqualTo(alternative3);
    assertThat(multipleChoiceQuestion.getAlternative4()).isEqualTo(alternative4);
    assertThat(multipleChoiceQuestion.getCorrectAlternatives()).isEqualTo(correctAlternative);
  }
}

