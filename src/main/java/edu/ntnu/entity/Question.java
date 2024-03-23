package edu.ntnu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {
  @Id

  private long questionId;

  public Question() {
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }



}
