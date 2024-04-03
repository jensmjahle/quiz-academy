package edu.ntnu.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents a quiz in the system. A quiz is a collection of questions that a user can answer.
 */
@Entity
@Table(name = "quizzes")
public class QuizDAO {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quizIdGenerator")
  @SequenceGenerator(name = "quizIdGenerator", sequenceName = "quiz_sequence", allocationSize = 1)

  private Long quizId;
  private String quizName;
  private String quizDescription;
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
  private List<TagDAO> tagDAOS = new ArrayList<>();

  @Column(nullable = false)
  private Date quizCreationDate;


  public QuizDAO() {
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  public String getQuizName() {
    return quizName;
  }

  public void setQuizName(String quizName) {
    this.quizName = quizName;
  }

  public String getQuizDescription() {
    return quizDescription;
  }

  public void setQuizDescription(String quizDescription) {
    this.quizDescription = quizDescription;
  }

  public UserDAO getUser() {
    return userDAO;
  }

  public void setUser(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public List<TagDAO> getTags() {
    return tagDAOS;
  }

  public void setTags(List<TagDAO> tagDAOS) {
    this.tagDAOS = tagDAOS;
  }

  public Date getQuizCreationDate() {
    return quizCreationDate;
  }

  public void setQuizCreationDate(Date quizCreationDate) {
    this.quizCreationDate = quizCreationDate;
  }

  public void addTag(TagDAO tagDAO) {
    tagDAOS.add(tagDAO);
  }

  public void removeTag(TagDAO tagDAO) {
    tagDAOS.remove(tagDAO);
  }


}


