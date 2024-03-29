package edu.ntnu.repositoryTests.questionsTests;

import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.model.questions.TextInputQuestion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import edu.ntnu.repository.questions.TextInputQuestionRepository;

@DataJpaTest
@DisplayName("TextInputQuestion Repository Tests")
public class TextInputQuestionRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TextInputQuestionRepository textInputQuestionRepository;

  @Test
  @DisplayName("TextInputQuestion is saved to the database correctly")
  public void testSaveTextInputQuestion() {
    // Create a TextInputQuestion
    TextInputQuestion textInputQuestion = getTextInputQuestion();

    // Save the TextInputQuestion to the database
    TextInputQuestion savedQuestion = textInputQuestionRepository.save(textInputQuestion);

    // Retrieve the TextInputQuestion from the database
    TextInputQuestion retrievedQuestion = entityManager.find(TextInputQuestion.class, savedQuestion.getQuestionId());

    // Check that the TextInputQuestion was saved correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(textInputQuestion.getQuestionText());
  }

  @Test
  @DisplayName("TextInputQuestion is deleted from the database correctly")
  public void testDeleteTextInputQuestion() {
    // Create a TextInputQuestion
    TextInputQuestion textInputQuestion = getTextInputQuestion();

    // Save the TextInputQuestion to the database
    TextInputQuestion savedQuestion = entityManager.persist(textInputQuestion);

    // Delete the TextInputQuestion from the database
    textInputQuestionRepository.delete(savedQuestion);

    // Check that the TextInputQuestion was deleted correctly
    assertThat(entityManager.find(TextInputQuestion.class, savedQuestion.getQuestionId())).isNull();
  }

  @Test
  @DisplayName("TextInputQuestion is updated in the database correctly")
  public void testUpdateTextInputQuestion() {
    // Create a TextInputQuestion
    TextInputQuestion textInputQuestion = getTextInputQuestion();

    // Save the TextInputQuestion to the database
    TextInputQuestion savedQuestion = entityManager.persist(textInputQuestion);

    // Update the TextInputQuestion
    savedQuestion.setQuestionText("Updated question text");
    savedQuestion.setAnswer("Updated answer");

    // Save the updated TextInputQuestion to the database
    TextInputQuestion updatedQuestion = textInputQuestionRepository.save(savedQuestion);

    // Retrieve the updated TextInputQuestion from the database
    TextInputQuestion retrievedQuestion = entityManager.find(TextInputQuestion.class, updatedQuestion.getQuestionId());

    // Check that the TextInputQuestion was updated correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(savedQuestion.getQuestionText());
    assertThat(retrievedQuestion.getAnswer()).isEqualTo(savedQuestion.getAnswer());
  }

  private static TextInputQuestion getTextInputQuestion() {
    TextInputQuestion textInputQuestion = new TextInputQuestion();
    textInputQuestion.setQuestionText("Sample text input question");
    textInputQuestion.setAnswer("Sample answer");
    return textInputQuestion;
  }
}

