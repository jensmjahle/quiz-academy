package edu.ntnu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a tag in the system. A tag is a category or keyword that describes a quiz.
 */
@Entity
@Table(name = "tags")
public class Tag {
  @Id
  @Column(nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long tagId;
  @Column(nullable = false)
  private String tagName;

  public Tag() {
  }

  public Tag(String tagName) {
    this.tagName = tagName;
  }

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
