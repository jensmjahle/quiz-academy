package edu.ntnu.modelTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.TagDAO;
import edu.ntnu.dao.UserDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Quiz Tests")
public class QuizDAOTest {

  @Test
  @DisplayName("Quiz is correctly instantiated")
  public void testQuizInstantiation() {
    // Arrange
    String quizName = "Test Quiz";
    String quizDescription = "This is a test quiz";
    Date quizCreationDate = new Date();
    UserDAO userDAO = new UserDAO("testUser", "password", "test@example.com", "John", "Doe");
    List<TagDAO> tagDAOS = new ArrayList<>();

    // Act
    QuizDAO quizDAO = new QuizDAO();
    quizDAO.setQuizName(quizName);
    quizDAO.setQuizDescription(quizDescription);
    quizDAO.setQuizCreationDate(quizCreationDate);
    quizDAO.setUser(userDAO);
    quizDAO.setTags(tagDAOS);
    quizDAO.setQuizId(123L);

    // Assert
    assertThat(quizDAO).isNotNull();
    assertThat(quizDAO.getQuizName()).isEqualTo(quizName);
    assertThat(quizDAO.getQuizDescription()).isEqualTo(quizDescription);
    assertThat(quizDAO.getQuizCreationDate()).isEqualTo(quizCreationDate);
    assertThat(quizDAO.getUser()).isEqualTo(userDAO);
    assertThat(quizDAO.getTags()).isEqualTo(tagDAOS);
    assertThat(quizDAO.getQuizId()).isEqualTo(123L);
  }

  @Test
  @DisplayName("Adding and removing tags to quiz works correctly")
  public void testAddingAndRemovingTags() {
    // Arrange
    QuizDAO quizDAO = new QuizDAO();
    TagDAO tagDAO1 = new TagDAO("Tag1");
    tagDAO1.setTagId(121);
    TagDAO tagDAO2 = new TagDAO("Tag2");
    tagDAO2.setTagId(122);

    // Act
    quizDAO.addTag(tagDAO1);
    quizDAO.addTag(tagDAO2);
    quizDAO.removeTag(tagDAO1);

    // Assert
    assertThat(quizDAO.getTags()).contains(tagDAO2);
    assertThat(quizDAO.getTags()).doesNotContain(tagDAO1);
  }

  @Test
  @DisplayName("Getters and setters work correctly")
  public void testGettersAndSetters() {
    // Arrange
    QuizDAO quizDAO = new QuizDAO();
    String quizName = "Test Quiz";
    String quizDescription = "This is a test quiz";
    Date quizCreationDate = new Date();
    UserDAO userDAO = new UserDAO("testUser", "password", "test@example.com", "John", "Doe");
    List<TagDAO> tagDAOS = new ArrayList<>();
    TagDAO tagDAO = new TagDAO("Tag");

    // Act
    quizDAO.setQuizName(quizName);
    quizDAO.setQuizDescription(quizDescription);
    quizDAO.setQuizCreationDate(quizCreationDate);
    quizDAO.setUser(userDAO);
    quizDAO.setTags(tagDAOS);
    quizDAO.addTag(tagDAO);

    // Assert
    assertThat(quizDAO.getQuizName()).isEqualTo(quizName);
    assertThat(quizDAO.getQuizDescription()).isEqualTo(quizDescription);
    assertThat(quizDAO.getQuizCreationDate()).isEqualTo(quizCreationDate);
    assertThat(quizDAO.getUser()).isEqualTo(userDAO);
    assertThat(quizDAO.getTags()).isEqualTo(tagDAOS);
    assertThat(quizDAO.getTags()).contains(tagDAO);
  }
}

