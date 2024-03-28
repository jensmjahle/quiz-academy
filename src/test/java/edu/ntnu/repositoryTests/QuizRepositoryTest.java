package edu.ntnu.repositoryTests;

import edu.ntnu.model.Quiz;
import edu.ntnu.model.Tag;
import edu.ntnu.model.User;
import edu.ntnu.repository.QuizRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Quiz Repository Tests")
public class QuizRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private QuizRepository quizRepository;

  @Test
  @DisplayName("Quiz is saved to the database correctly")
  public void testSaveQuiz() {
    // Create a user
    User user = new User("username", "password", "email", "firstName", "lastName");

    // Persist the user
    entityManager.persist(user);

    // Create tags
    Tag tag1 = new Tag("Tag1");
    Tag tag2 = new Tag("Tag2");

    // Persist the tags
    entityManager.persist(tag1);
    entityManager.persist(tag2);

    // Add tags to a list
    List<Tag> tags = new ArrayList<>();
    tags.add(tag1);
    tags.add(tag2);

    // Create a quiz
    Quiz quiz = new Quiz();
    quiz.setQuizName("Test Quiz");
    quiz.setQuizDescription("Description of test quiz");
    quiz.setUser(user);
    quiz.setTags(tags);
    quiz.setQuizCreationDate(new Date());

    // Save the quiz to the database
    Quiz savedQuiz = quizRepository.save(quiz);

    // Retrieve the quiz from the database
    Quiz retrievedQuiz = entityManager.find(Quiz.class, savedQuiz.getQuizId());

    // Check that the quiz was saved correctly
    assertThat(retrievedQuiz.getQuizName()).isEqualTo(quiz.getQuizName());
    assertThat(retrievedQuiz.getQuizDescription()).isEqualTo(quiz.getQuizDescription());
    assertThat(retrievedQuiz.getUser().getUsername()).isEqualTo(user.getUsername());
    assertThat(retrievedQuiz.getTags().size()).isEqualTo(tags.size());
  }

  @Test
  @DisplayName("Quiz is deleted from the database correctly")
  public void testDeleteQuiz() {
    // Create a quiz
    Quiz quiz = new Quiz();
    quiz.setQuizName("Test Quiz");
    quiz.setQuizDescription("Description of test quiz");
    quiz.setQuizCreationDate(new Date());
    entityManager.persist(quiz);

    // Delete the quiz from the repository
    quizRepository.delete(quiz);

    // Check that the quiz was deleted successfully
    assertThat(quizRepository.findByQuizName(quiz.getQuizName())).isNull();
  }

  @Test
  @DisplayName("Quiz is retrieved from the database correctly")
  public void testGetQuiz() {
    // Create a quiz
    Quiz quiz = new Quiz();
    quiz.setQuizName("Test Quiz");
    quiz.setQuizDescription("Description of test quiz");
    quiz.setQuizCreationDate(new Date());
    entityManager.persist(quiz);

    // Retrieve the quiz from the repository
    Quiz retrievedQuiz = quizRepository.findByQuizName(quiz.getQuizName());

    // Check that the quiz was retrieved successfully
    assertThat(retrievedQuiz.getQuizName()).isEqualTo(quiz.getQuizName());
    assertThat(retrievedQuiz.getQuizDescription()).isEqualTo(quiz.getQuizDescription());
  }
}