package edu.ntnu.repositoryTests.questionsTests;

import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.model.questions.MultipleChoiceQuestion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import edu.ntnu.repository.questions.MultipleChoiceQuestionRepository;

@DataJpaTest
@DisplayName("MultipleChoiceQuestion Repository Tests")
public class MultipleChoiceQuestionRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

  @Test
  @DisplayName("MultipleChoiceQuestion is saved to the database correctly")
  public void testSaveMultipleChoiceQuestion() {
    // Create a MultipleChoiceQuestion
    MultipleChoiceQuestion multipleChoiceQuestion = getMultipleChoiceQuestion();

    // Save the MultipleChoiceQuestion to the database
    MultipleChoiceQuestion savedQuestion = multipleChoiceQuestionRepository.save(multipleChoiceQuestion);

    // Retrieve the MultipleChoiceQuestion from the database
    MultipleChoiceQuestion retrievedQuestion = entityManager.find(MultipleChoiceQuestion.class, savedQuestion.getQuestionId());

    // Check that the MultipleChoiceQuestion was saved correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(multipleChoiceQuestion.getQuestionText());
  }

  @Test
  @DisplayName("MultipleChoiceQuestion is deleted from the database correctly")
  public void testDeleteMultipleChoiceQuestion() {
    // Create a MultipleChoiceQuestion
    MultipleChoiceQuestion multipleChoiceQuestion = getMultipleChoiceQuestion();

    // Save the MultipleChoiceQuestion to the database
    MultipleChoiceQuestion savedQuestion = entityManager.persist(multipleChoiceQuestion);

    // Delete the MultipleChoiceQuestion from the database
    multipleChoiceQuestionRepository.delete(savedQuestion);

    // Check that the MultipleChoiceQuestion was deleted correctly
    assertThat(entityManager.find(MultipleChoiceQuestion.class, savedQuestion.getQuestionId())).isNull();
  }

  @Test
  @DisplayName("MultipleChoiceQuestion is updated in the database correctly")
  public void testUpdateMultipleChoiceQuestion() {
    // Create a MultipleChoiceQuestion
    MultipleChoiceQuestion multipleChoiceQuestion = getMultipleChoiceQuestion();

    // Save the MultipleChoiceQuestion to the database
    MultipleChoiceQuestion savedQuestion = entityManager.persist(multipleChoiceQuestion);

    // Update the MultipleChoiceQuestion
    savedQuestion.setQuestionText("Updated question text");
    savedQuestion.setCorrectAlternative("Updated correct alternative");
    savedQuestion.setAlternative1("Updated alternative 1");
    savedQuestion.setAlternative2("Updated alternative 2");
    savedQuestion.setAlternative3("Updated alternative 3");
    savedQuestion.setAlternative4("Updated alternative 4");

    // Save the updated MultipleChoiceQuestion to the database
    MultipleChoiceQuestion updatedQuestion = multipleChoiceQuestionRepository.save(savedQuestion);

    // Retrieve the updated MultipleChoiceQuestion from the database
    MultipleChoiceQuestion retrievedQuestion = entityManager.find(MultipleChoiceQuestion.class, updatedQuestion.getQuestionId());

    // Check that the MultipleChoiceQuestion was updated correctly
    assertThat(retrievedQuestion.getQuestionText()).isEqualTo(savedQuestion.getQuestionText());
    assertThat(retrievedQuestion.getCorrectAlternative()).isEqualTo(savedQuestion.getCorrectAlternative());
    assertThat(retrievedQuestion.getAlternative1()).isEqualTo(savedQuestion.getAlternative1());
    assertThat(retrievedQuestion.getAlternative2()).isEqualTo(savedQuestion.getAlternative2());
    assertThat(retrievedQuestion.getAlternative3()).isEqualTo(savedQuestion.getAlternative3());
    assertThat(retrievedQuestion.getAlternative4()).isEqualTo(savedQuestion.getAlternative4());
  }


  private static MultipleChoiceQuestion getMultipleChoiceQuestion() {
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setQuestionText("Sample multiple choice question");
    multipleChoiceQuestion.setCorrectAlternative("Sample alternative 1");
    multipleChoiceQuestion.setAlternative1("Sample alternative 1");
    multipleChoiceQuestion.setAlternative2("Sample alternative 2");
    multipleChoiceQuestion.setAlternative3("Sample alternative 3");
    multipleChoiceQuestion.setAlternative4("Sample alternative 4");
    return multipleChoiceQuestion;
  }

}
