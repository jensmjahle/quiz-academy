package edu.ntnu.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import jdk.internal.icu.text.UnicodeSet;


@Entity
@Table(name = "quizzes")
public class Quiz{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long quizId;
  private String quizName;
  private String quizDescription;
  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "createdBy", referencedColumnName = "username")
  private User user;
  @ManyToMany
  @JoinTable(
      name = "quiz_tags",
      joinColumns = @JoinColumn(name = "quizId"),
      inverseJoinColumns = @JoinColumn(name = "tagId")
  )
  private List<Tag> tags;

  @Column(nullable = false)
  private Date quiz_creation_date;


  public Quiz() {
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Date getQuiz_creation_date() {
    return quiz_creation_date;
  }

  public void setQuiz_creation_date(Date quiz_creation_date) {
    this.quiz_creation_date = quiz_creation_date;
  }

  public void addTag(Tag tag) {
    tags.add(tag);
  }

  public void removeTag(Tag tag) {
    tags.remove(tag);
  }


}


