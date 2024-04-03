package edu.ntnu.repositoryTests;

import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.TagDAO;
import edu.ntnu.dao.UserDAO;
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
public class QuizDAORepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private QuizRepository quizRepository;

  @Test
  @DisplayName("Quiz is saved to the database correctly")
  public void testSaveQuiz() {
    // Create a user
    UserDAO userDAO = new UserDAO("username", "password", "email", "firstName", "lastName");

    // Persist the user
    entityManager.persist(userDAO);

    // Create tags
    TagDAO tagDAO1 = new TagDAO("Tag1");
    TagDAO tagDAO2 = new TagDAO("Tag2");

    // Persist the tags
    entityManager.persist(tagDAO1);
    entityManager.persist(tagDAO2);

    // Add tags to a list
    List<TagDAO> tagDAOS = new ArrayList<>();
    tagDAOS.add(tagDAO1);
    tagDAOS.add(tagDAO2);

    // Create a quiz
    QuizDAO quizDAO = new QuizDAO();
    quizDAO.setQuizName("Test Quiz");
    quizDAO.setQuizDescription("Description of test quiz");
    quizDAO.setUser(userDAO);
    quizDAO.setTags(tagDAOS);
    quizDAO.setQuizCreationDate(new Date());

    // Save the quiz to the database
    QuizDAO savedQuizDAO = quizRepository.save(quizDAO);

    // Retrieve the quiz from the database
    QuizDAO retrievedQuizDAO = entityManager.find(QuizDAO.class, savedQuizDAO.getQuizId());

    // Check that the quiz was saved correctly
    assertThat(retrievedQuizDAO.getQuizName()).isEqualTo(quizDAO.getQuizName());
    assertThat(retrievedQuizDAO.getQuizDescription()).isEqualTo(quizDAO.getQuizDescription());
    assertThat(retrievedQuizDAO.getUser().getUsername()).isEqualTo(userDAO.getUsername());
    assertThat(retrievedQuizDAO.getTags().size()).isEqualTo(tagDAOS.size());
  }

  @Test
  @DisplayName("Quiz is deleted from the database correctly")
  public void testDeleteQuiz() {
    // Create a quiz
    QuizDAO quizDAO = new QuizDAO();
    quizDAO.setQuizName("Test Quiz");
    quizDAO.setQuizDescription("Description of test quiz");
    quizDAO.setQuizCreationDate(new Date());
    entityManager.persist(quizDAO);

    // Delete the quiz from the repository
    quizRepository.delete(quizDAO);

    // Check that the quiz was deleted successfully
    assertThat(quizRepository.findByQuizName(quizDAO.getQuizName())).isNull();
  }

  @Test
  @DisplayName("Quiz is retrieved from the database correctly")
  public void testGetQuiz() {
    // Create a quiz
    QuizDAO quizDAO = new QuizDAO();
    quizDAO.setQuizName("Test Quiz");
    quizDAO.setQuizDescription("Description of test quiz");
    quizDAO.setQuizCreationDate(new Date());
    entityManager.persist(quizDAO);

    // Retrieve the quiz from the repository
    QuizDAO retrievedQuizDAO = quizRepository.findByQuizName(quizDAO.getQuizName());

    // Check that the quiz was retrieved successfully
    assertThat(retrievedQuizDAO.getQuizName()).isEqualTo(quizDAO.getQuizName());
    assertThat(retrievedQuizDAO.getQuizDescription()).isEqualTo(quizDAO.getQuizDescription());
  }
}