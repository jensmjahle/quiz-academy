package edu.ntnu.entity;

import antlr.collections.List;
import com.sun.tools.javac.code.Attribute.Enum;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Quiz implements Serializable {
  @Id
  private Long quizId;
  private String quizName;
  private String quizDescription;
  @OneToMany
  private List<Question> questions;
  @ManyToOne
  private User createdBy;
  @OneToMany
  private List<Tag> tags;
  private List<Category> category;
  private Enum difficulty;

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

  public <Question> getQuestions() {
    return questions;
  }

  public void setQuestions(<Question> questions) {
    this.questions = questions;
  }

  public <User> getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(<User> createdBy) {
    this.createdBy = createdBy;
  }

  public <Tag> getTags() {
    return tags;
  }

  public void setTags(<Tag> tags) {
    this.tags = tags;
  }

  public <Category> getCategory() {
    return category;
  }

  public void setCategory(<Category> category) {
    this.category = category;
  }

  public <Difficulty> getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(<Difficulty> difficulty) {
    this.difficulty = difficulty;
  }


}
