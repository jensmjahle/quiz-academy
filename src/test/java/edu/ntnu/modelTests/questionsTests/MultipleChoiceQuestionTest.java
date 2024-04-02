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
    String alternatives = "Option 1*Option 2*Option 3*Option 4";
    String correctAlternative = "Option 1";

    // Act
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setAlternatives(alternatives);
    multipleChoiceQuestion.setCorrectAlternatives(correctAlternative);

    // Assert
    assertThat(multipleChoiceQuestion).isNotNull();
    assertThat(multipleChoiceQuestion.getAlternatives()).isEqualTo(alternatives);
    assertThat(multipleChoiceQuestion.getCorrectAlternatives()).isEqualTo(correctAlternative);
  }

  @Test
  @DisplayName("Getters and setters work correctly")
  public void testGettersAndSetters() {
    // Arrange
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    String alternatives = "Option 1*Option 2*Option 3*Option 4";
    String correctAlternative = "Option 1";

    // Act
    multipleChoiceQuestion.setAlternatives(alternatives);
    multipleChoiceQuestion.setCorrectAlternatives(correctAlternative);

    // Assert
    assertThat(multipleChoiceQuestion.getAlternatives()).isEqualTo(alternatives);
    assertThat(multipleChoiceQuestion.getCorrectAlternatives()).isEqualTo(correctAlternative);
  }
}

