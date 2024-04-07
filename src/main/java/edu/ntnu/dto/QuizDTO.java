package edu.ntnu.dto;

import edu.ntnu.dto.questions.QuestionDTO;
import java.util.Date;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 * Data transfer object for the Quiz entity.
 */
public class QuizDTO {
  private Long quizId;
  private String quizName;
  private String quizDescription;
  private String user;
  private boolean isPublic;
  @Nullable
  private List<TagDTO> tags;
  @Nullable
  private Date quizCreationDate;
  @Nullable
  private List<QuestionDTO> questions;

  /**
   * Constructor for the QuizDTO class.
   */
  public QuizDTO() {
  }

  /**
   * Constructor for the QuizDTO class.
   * @param quizId the id of the quiz
   * @param quizName the name of the quiz
   * @param quizDescription the description of the quiz
   * @param user the user that created the quiz
   * @param tags the tags of the quiz
   * @param quizCreationDate the creation date of the quiz
   * @param questions the questions of the quiz
   */
  public QuizDTO(Long quizId, String quizName, String quizDescription, String user, List<TagDTO> tags, Date quizCreationDate, List<QuestionDTO> questions, boolean isPublic) {
    this.quizId = quizId;
    this.quizName = quizName;
    this.quizDescription = quizDescription;
    this.user = user;
    this.tags = tags;
    this.quizCreationDate = quizCreationDate;
    this.questions = questions;
    this.isPublic = isPublic;

  }

  /**
   * Getter for the quiz id.
   * @return the quiz id
   */
  public Long getQuizId() {
    return quizId;
  }

  /**
   * Setter for the quiz id.
   * @param quizId the quiz id
   */
  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  /**
   * Getter for the quiz name.
   * @return the quiz name
   */
  public String getQuizName() {
    return quizName;
  }

  /**
   * Setter for the quiz name.
   * @param quizName the quiz name
   */
  public void setQuizName(String quizName) {
    this.quizName = quizName;
  }

  /**
   * Getter for the quiz description.
   * @return the quiz description
   */
  public String getQuizDescription() {
    return quizDescription;
  }

  /**
   * Setter for the quiz description.
   * @param quizDescription the quiz description
   */
  public void setQuizDescription(String quizDescription) {
    this.quizDescription = quizDescription;
  }

  /**
   * Getter for the user.
   * @return the user
   */
  public String getUser() {
    return user;
  }

  /**
   * Setter for the user.
   * @param username the user
   */
  public void setUser(String username) {
    this.user = username;
  }

  /**
   * Getter for the tags.
   * @return the tags
   */
  public List<TagDTO> getTags() {
    return tags;
  }

  /**
   * Setter for the tags.
   * @param tags the tags
   */
  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }

  /**
   * Getter for the quiz creation date.
   * @return the quiz creation date
   */
  public Date getQuizCreationDate() {
    return quizCreationDate;
  }

  /**
   * Setter for the quiz creation date.
   * @param quizCreationDate the quiz creation date
   */
  public void setQuizCreationDate(Date quizCreationDate) {
    this.quizCreationDate = quizCreationDate;
  }

  /**
   * Setter for the questions.
   * @param questions the questions
   */
  public void setQuestions(List<QuestionDTO> questions) {
    this.questions = questions;
  }

  /**
   * Adds a question to the quiz.
   * @param question the question to add
   */
  public void addQuestion(QuestionDTO question) {
    this.questions.add(question);
  }

  /**
   * Removes a question from the quiz.
   * @param question the question to remove
   */
  public void removeQuestion(QuestionDTO question) {
    this.questions.remove(question);
  }

  /**
   * Adds a tag to the quiz.
   * @param tag the tag to add
   */
  public void addTag(TagDTO tag) {
    this.tags.add(tag);
  }

  /**
   * Removes a tag from the quiz.
   * @param tag the tag to remove
   */
  public void removeTag(TagDTO tag) {
    this.tags.remove(tag);
  }

  /**
   * Getter for the isPublic.
   * @return the isPublic
   */
  public boolean isPublic() {
    return isPublic;
  }

  /**
   * Setter for the isPublic.
   * @param isPublic the isPublic
   */
  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  /**
   * Returns a string representation of the QuizDTO object.
   * @return the string representation
   */
  @Override
  public String toString() {
    return "QuizDTO{" +
        "quizId=" + quizId +
        ", quizName='" + quizName + '\'' +
        ", quizDescription='" + quizDescription + '\'' +
        ", user=" + user +
        ", tags=" + tags +
        ", quizCreationDate=" + quizCreationDate +
        ", questions=" + questions +
        '}';
  }

  /**
   * Getter for the questions.
   * @return the questions
   */
  public List<QuestionDTO> getQuestions() {
    return questions;
  }
}
