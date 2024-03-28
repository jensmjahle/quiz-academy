package edu.ntnu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name = "tags")
public class Tag {
  @Id
  @Column(nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long tagId;
  @Column(nullable = false)
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
