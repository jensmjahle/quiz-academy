package edu.ntnu.DTOTests.questionsTests;

import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultipleChoiceQuestionDAOTestDAO {

  @Test
  @DisplayName("MultipleChoiceQuestion is correctly instantiated")
  public void testMultipleChoiceQuestionInstantiation() {
    // Arrange
    String alternatives = "Option 1*Option 2*Option 3*Option 4";
    String correctAlternative = "Option 1";

    // Act
    MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = new MultipleChoiceQuestionDAO();
    multipleChoiceQuestionDAO.setAlternatives(alternatives);
    multipleChoiceQuestionDAO.setCorrectAlternatives(correctAlternative);

    // Assert
    assertThat(multipleChoiceQuestionDAO).isNotNull();
    assertThat(multipleChoiceQuestionDAO.getAlternatives()).isEqualTo(alternatives);
    assertThat(multipleChoiceQuestionDAO.getCorrectAlternatives()).isEqualTo(correctAlternative);
  }

  @Test
  @DisplayName("Getters and setters work correctly")
  public void testGettersAndSetters() {
    // Arrange
    MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = new MultipleChoiceQuestionDAO();
    String alternatives = "Option 1*Option 2*Option 3*Option 4";
    String correctAlternative = "Option 1";

    // Act
    multipleChoiceQuestionDAO.setAlternatives(alternatives);
    multipleChoiceQuestionDAO.setCorrectAlternatives(correctAlternative);

    // Assert
    assertThat(multipleChoiceQuestionDAO.getAlternatives()).isEqualTo(alternatives);
    assertThat(multipleChoiceQuestionDAO.getCorrectAlternatives()).isEqualTo(correctAlternative);
  }
}

