package edu.ntnu.modelTests.questionsTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.model.questions.TextInputQuestion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TextInputQuestionTest {

  @Test
  @DisplayName("TextInputQuestion is correctly instantiated")
  public void testTextInputQuestionInstantiation() {
    // Arrange
    String answer = "Test answer";

    // Act
    TextInputQuestion textInputQuestion = new TextInputQuestion();
    textInputQuestion.setAnswer(answer);

    // Assert
    assertThat(textInputQuestion).isNotNull();
    assertThat(textInputQuestion.getAnswer()).isEqualTo(answer);
  }

  @Test
  @DisplayName("Getters and setters work correctly")
  public void testGettersAndSetters() {
    // Arrange
    TextInputQuestion textInputQuestion = new TextInputQuestion();
    String answer = "Test answer";

    // Act
    textInputQuestion.setAnswer(answer);

    // Assert
    assertThat(textInputQuestion.getAnswer()).isEqualTo(answer);
  }
}
