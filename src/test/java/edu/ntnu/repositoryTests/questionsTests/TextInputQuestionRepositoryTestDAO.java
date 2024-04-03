package edu.ntnu.repositoryTests.questionsTests;

import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.dao.questions.TextInputQuestionDAO;
import edu.ntnu.repository.questions.TextInputQuestionDAORepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@DisplayName("TextInputQuestion Repository Tests")
public class TextInputQuestionRepositoryTestDAO {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TextInputQuestionDAORepository textInputQuestionRepository;

  @Test
  @DisplayName("TextInputQuestion is saved to the database correctly")
  public void testSaveTextInputQuestion() {
    // Create a TextInputQuestion
    TextInputQuestionDAO textInputQuestion = getTextInputQuestion();

    // Save the TextInputQuestion to the database
    TextInputQuestionDAO savedQuestion = textInputQuestionRepository.save(textInputQuestion);

    // Retrieve the TextInputQuestion from the database
    TextInputQuestionDAO retrievedQuestion = entityManager.find(TextInputQuestionDAO.class, savedQuestion.getQuestionId());

    // Check that the TextInputQuestion was saved correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(textInputQuestion.getQuestionText());
  }

  @Test
  @DisplayName("TextInputQuestion is deleted from the database correctly")
  public void testDeleteTextInputQuestion() {
    // Create a TextInputQuestion
    TextInputQuestionDAO textInputQuestion = getTextInputQuestion();

    // Save the TextInputQuestion to the database
    TextInputQuestionDAO savedQuestion = entityManager.persist(textInputQuestion);

    // Delete the TextInputQuestion from the database
    textInputQuestionRepository.delete(savedQuestion);

    // Check that the TextInputQuestion was deleted correctly
    assertThat(entityManager.find(TextInputQuestionDAO.class, savedQuestion.getQuestionId())).isNull();
  }

  @Test
  @DisplayName("TextInputQuestion is updated in the database correctly")
  public void testUpdateTextInputQuestion() {
    // Create a TextInputQuestion
    TextInputQuestionDAO textInputQuestion = getTextInputQuestion();

    // Save the TextInputQuestion to the database
    TextInputQuestionDAO savedQuestion = entityManager.persist(textInputQuestion);

    // Update the TextInputQuestion
    savedQuestion.setQuestionText("Updated question text");
    savedQuestion.setAnswer("Updated answer");

    // Save the updated TextInputQuestion to the database
    TextInputQuestionDAO updatedQuestion = textInputQuestionRepository.save(savedQuestion);

    // Retrieve the updated TextInputQuestion from the database
    TextInputQuestionDAO retrievedQuestion = entityManager.find(TextInputQuestionDAO.class, updatedQuestion.getQuestionId());

    // Check that the TextInputQuestion was updated correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(savedQuestion.getQuestionText());
    assertThat(retrievedQuestion.getAnswer()).isEqualTo(savedQuestion.getAnswer());
  }

  private static TextInputQuestionDAO getTextInputQuestion() {
    TextInputQuestionDAO textInputQuestion = new TextInputQuestionDAO();
    textInputQuestion.setQuestionText("Sample text input question");
    textInputQuestion.setAnswer("Sample answer");
    return textInputQuestion;
  }
}

