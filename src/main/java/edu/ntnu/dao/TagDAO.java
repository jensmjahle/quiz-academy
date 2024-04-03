package edu.ntnu.dao;

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
public class TagDAO {
  @Id
  @Column(nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long tagId;
  @Column(nullable = false)
  private String tagName;

  public TagDAO() {
  }

  public TagDAO( String tagName) {
    this.tagName = tagName;
  }
  public TagDAO(long tagId, String tagName) {
    this.tagId = tagId;
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

  @Override
  public String toString() {
    return "Tag{" +
        "tagId=" + tagId +
        ", tagName='" + tagName + '\'' +
        '}';
  }
}
