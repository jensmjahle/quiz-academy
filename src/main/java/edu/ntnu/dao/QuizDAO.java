package edu.ntnu.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Lob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;

/**
 * Represents a quiz in the system. A quiz is a collection of questions that a user can answer.
 */
@Entity
public class QuizDAO {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quizIdGenerator")
  @SequenceGenerator(name = "quizIdGenerator", sequenceName = "quiz_sequence", allocationSize = 1)

  private Long quizId;
  private String quizName;
  private String quizDescription;
  private boolean isPublic;
  @Lob
  @Column(name = "image", nullable = true)
  private byte[] quizImage;
  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "createdBy", referencedColumnName = "username")
  private UserDAO userDAO;
  @ManyToMany
  @JoinTable(
      name = "quiz_tags",
      joinColumns = @JoinColumn(name = "quizId"),
      inverseJoinColumns = @JoinColumn(name = "tagId")
  )
  private List<TagDAO> tagDAOs = new ArrayList<>();

  @Column(nullable = false)
  private Date quizCreationDate;

  /**
   * Creates a new instance of the Quiz class.
   */
  public QuizDAO() {
  }

  /**
   * Creates a new instance of the Quiz class.
   *
   * @param quizName the name of the quiz
   * @param quizDescription the description of the quiz
   * @param userDAO the user that created the quiz
   * @param tags the tags of the quiz
   * @param quizCreationDate the creation date of the quiz
   */
  public Long getQuizId() {
    return quizId;
  }

  public QuizDAO(String quizName, String quizDescription, UserDAO userDAO, List<TagDAO> tagDAOs, Date quizCreationDate, boolean isPublic) {
    this.quizName = quizName;
    this.quizDescription = quizDescription;
    this.userDAO = userDAO;
    this.tagDAOs = tagDAOs;
    this.quizCreationDate = quizCreationDate;
    this.isPublic = isPublic;
  }
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
   * Getter for the user that created the quiz.
   * @return the user that created the quiz
   */
  public UserDAO getUser() {
    return userDAO;
  }

  /**
   * Setter for the user that created the quiz.
   * @param userDAO the user that created the quiz
   */
  public void setUser(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  /**
   * Getter for the tags of the quiz.
   * @return the tags of the quiz
   */
  public List<TagDAO> getTags() {
    return tagDAOs;
  }

  /**
   * Setter for the tags of the quiz.
   * @param tagDAOS the tags of the quiz
   */
  public void setTags(List<TagDAO> tagDAOS) {
    this.tagDAOs = tagDAOS;
  }

  /**
   * Getter for the creation date of the quiz.
   * @return the creation date of the quiz
   */
  public Date getQuizCreationDate() {
    return quizCreationDate;
  }

  /**
   * Setter for the creation date of the quiz.
   * @param quizCreationDate the creation date of the quiz
   */
  public void setQuizCreationDate(Date quizCreationDate) {
    this.quizCreationDate = quizCreationDate;
  }

  /**
   * Add a tag to the quiz.
   * @param tagDAO the tag to add
   */
  public void addTag(TagDAO tagDAO) {
    tagDAOs.add(tagDAO);
  }

  /**
   * Remove a tag from the quiz.
   * @param tagDAO the tag to remove
   */
  public void removeTag(TagDAO tagDAO) {
    tagDAOs.remove(tagDAO);
  }

  /**
   * Getter for the isPublic field.
   * @return the isPublic field
   */
  public boolean isPublic() {
    return isPublic;
  }

  /**
   * Setter for the isPublic field.
   * @param isPublic the isPublic field
   */
  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

public byte[] getQuizImage() {
  return quizImage;
}
public void setQuizImage(byte[] quizImage) {
  this.quizImage = quizImage;
}
}


