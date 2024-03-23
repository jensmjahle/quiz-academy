package edu.ntnu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
  @Id
  private long tagId;
  private String tagName;

  public Tag(){}

  public long getTagId() {
    return tagId;
  }

  public void setTagId(long tagId) {
    this.tagId = tagId;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }
}
