package edu.ntnu.modelTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.model.Quiz;
import edu.ntnu.model.Tag;
import edu.ntnu.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Quiz Tests")
public class QuizTest {

  @Test
  @DisplayName("Quiz is correctly instantiated")
  public void testQuizInstantiation() {
    // Arrange
    String quizName = "Test Quiz";
    String quizDescription = "This is a test quiz";
    Date quizCreationDate = new Date();
    User user = new User("testUser", "password", "test@example.com", "John", "Doe");
    List<Tag> tags = new ArrayList<>();

    // Act
    Quiz quiz = new Quiz();
    quiz.setQuizName(quizName);
    quiz.setQuizDescription(quizDescription);
    quiz.setQuizCreationDate(quizCreationDate);
    quiz.setUser(user);
    quiz.setTags(tags);
    quiz.setQuizId(123L);

    // Assert
    assertThat(quiz).isNotNull();
    assertThat(quiz.getQuizName()).isEqualTo(quizName);
    assertThat(quiz.getQuizDescription()).isEqualTo(quizDescription);
    assertThat(quiz.getQuizCreationDate()).isEqualTo(quizCreationDate);
    assertThat(quiz.getUser()).isEqualTo(user);
    assertThat(quiz.getTags()).isEqualTo(tags);
    assertThat(quiz.getQuizId()).isEqualTo(123L);
  }

  @Test
  @DisplayName("Adding and removing tags to quiz works correctly")
  public void testAddingAndRemovingTags() {
    // Arrange
    Quiz quiz = new Quiz();
    Tag tag1 = new Tag("Tag1");
    tag1.setTagId(121);
    Tag tag2 = new Tag("Tag2");
    tag2.setTagId(122);

    // Act
    quiz.addTag(tag1);
    quiz.addTag(tag2);
    quiz.removeTag(tag1);

    // Assert
    assertThat(quiz.getTags()).contains(tag2);
    assertThat(quiz.getTags()).doesNotContain(tag1);
  }

  @Test
  @DisplayName("Getters and setters work correctly")
  public void testGettersAndSetters() {
    // Arrange
    Quiz quiz = new Quiz();
    String quizName = "Test Quiz";
    String quizDescription = "This is a test quiz";
    Date quizCreationDate = new Date();
    User user = new User("testUser", "password", "test@example.com", "John", "Doe");
    List<Tag> tags = new ArrayList<>();
    Tag tag = new Tag("Tag");

    // Act
    quiz.setQuizName(quizName);
    quiz.setQuizDescription(quizDescription);
    quiz.setQuizCreationDate(quizCreationDate);
    quiz.setUser(user);
    quiz.setTags(tags);
    quiz.addTag(tag);

    // Assert
    assertThat(quiz.getQuizName()).isEqualTo(quizName);
    assertThat(quiz.getQuizDescription()).isEqualTo(quizDescription);
    assertThat(quiz.getQuizCreationDate()).isEqualTo(quizCreationDate);
    assertThat(quiz.getUser()).isEqualTo(user);
    assertThat(quiz.getTags()).isEqualTo(tags);
    assertThat(quiz.getTags()).contains(tag);
  }
}

