package edu.ntnu.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import edu.ntnu.dao.QuizDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


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
    return tagDAOs;
  }

  public void setTags(List<TagDAO> tagDAOS) {
    this.tagDAOs = tagDAOS;
  }

  public Date getQuizCreationDate() {
    return quizCreationDate;
  }

  public void setQuizCreationDate(Date quizCreationDate) {
    this.quizCreationDate = quizCreationDate;
  }

  public void addTag(TagDAO tagDAO) {
    tagDAOs.add(tagDAO);
  }

  public void removeTag(TagDAO tagDAO) {
    tagDAOs.remove(tagDAO);
  }

  public boolean isPublic() {
    return isPublic;
  }
  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

}


