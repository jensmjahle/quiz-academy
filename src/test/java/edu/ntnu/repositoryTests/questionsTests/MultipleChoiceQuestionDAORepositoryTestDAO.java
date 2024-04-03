package edu.ntnu.repositoryTests.questionsTests;

import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import edu.ntnu.repository.questions.MultipleChoiceQuestionDAORepository;

@DataJpaTest
@DisplayName("MultipleChoiceQuestion Repository Tests")
public class MultipleChoiceQuestionDAORepositoryTestDAO {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private MultipleChoiceQuestionDAORepository multipleChoiceQuestionRepository;

  @Test
  @DisplayName("MultipleChoiceQuestion is saved to the database correctly")
  public void testSaveMultipleChoiceQuestion() {
    // Create a MultipleChoiceQuestion
    MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = getMultipleChoiceQuestion();

    // Save the MultipleChoiceQuestion to the database
    MultipleChoiceQuestionDAO savedQuestion = multipleChoiceQuestionRepository.save(
        multipleChoiceQuestionDAO);

    // Retrieve the MultipleChoiceQuestion from the database
    MultipleChoiceQuestionDAO retrievedQuestion = entityManager.find(
        MultipleChoiceQuestionDAO.class, savedQuestion.getQuestionId());

    // Check that the MultipleChoiceQuestion was saved correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(multipleChoiceQuestionDAO.getQuestionText());
  }

  @Test
  @DisplayName("MultipleChoiceQuestion is deleted from the database correctly")
  public void testDeleteMultipleChoiceQuestion() {
    // Create a MultipleChoiceQuestion
    MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = getMultipleChoiceQuestion();

    // Save the MultipleChoiceQuestion to the database
    MultipleChoiceQuestionDAO savedQuestion = entityManager.persist(multipleChoiceQuestionDAO);

    // Delete the MultipleChoiceQuestion from the database
    multipleChoiceQuestionRepository.delete(savedQuestion);

    // Check that the MultipleChoiceQuestion was deleted correctly
    assertThat(entityManager.find(MultipleChoiceQuestionDAO.class, savedQuestion.getQuestionId())).isNull();
  }

  @Test
  @DisplayName("MultipleChoiceQuestion is updated in the database correctly")
  public void testUpdateMultipleChoiceQuestion() {
    // Create a MultipleChoiceQuestion
    MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = getMultipleChoiceQuestion();

    // Save the MultipleChoiceQuestion to the database
    MultipleChoiceQuestionDAO savedQuestion = entityManager.persist(multipleChoiceQuestionDAO);

    // Update the MultipleChoiceQuestion
    savedQuestion.setQuestionText("Updated question text");
    savedQuestion.setCorrectAlternatives("dog");
    savedQuestion.setAlternatives("cow*dog*cat*fish");

    // Save the updated MultipleChoiceQuestion to the database
    MultipleChoiceQuestionDAO updatedQuestion = multipleChoiceQuestionRepository.save(savedQuestion);

    // Retrieve the updated MultipleChoiceQuestion from the database
    MultipleChoiceQuestionDAO retrievedQuestion = entityManager.find(
        MultipleChoiceQuestionDAO.class, updatedQuestion.getQuestionId());

    // Check that the MultipleChoiceQuestion was updated correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(savedQuestion.getQuestionText());
    assertThat(retrievedQuestion.getCorrectAlternatives()).isEqualTo(savedQuestion.getCorrectAlternatives());
    assertThat(retrievedQuestion.getAlternatives()).isEqualTo(savedQuestion.getAlternatives());

  }


  private static MultipleChoiceQuestionDAO getMultipleChoiceQuestion() {
    MultipleChoiceQuestionDAO multipleChoiceQuestionDAO = new MultipleChoiceQuestionDAO();
    multipleChoiceQuestionDAO.setQuestionText("Sample multiple choice question");
    multipleChoiceQuestionDAO.setCorrectAlternatives("Sample alternative 1");
    multipleChoiceQuestionDAO.setAlternatives("Sample alternative 1*Sample alternative 2*Sample alternative 3*Sample alternative 4");
    return multipleChoiceQuestionDAO;
  }

}
