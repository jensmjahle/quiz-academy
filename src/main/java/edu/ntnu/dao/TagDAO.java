package edu.ntnu.dao;

import jakarta.persistence.*;


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

  /**
   * Creates a new instance of the Tag class.
   */
  public TagDAO() {
  }

  /**
   * Creates a new instance of the Tag class.
   *
   * @param tagName the name of the tag
   */
  public TagDAO( String tagName) {
    this.tagName = tagName;
  }

  /**
   * Creates a new instance of the Tag class.
   *
   * @param tagId the id of the tag
   * @param tagName the name of the tag
   */
  public TagDAO(long tagId, String tagName) {
    this.tagId = tagId;
    this.tagName = tagName;
  }

  /**
   * Returns the id of the tag.
   *
   * @return the id of the tag
   */
  public long getTagId() {
    return tagId;
  }

  /**
   * Sets the id of the tag.
   *
   * @param tagId the id of the tag
   */
  public void setTagId(long tagId) {
    this.tagId = tagId;
  }

  /**
   * Returns the name of the tag.
   *
   * @return the name of the tag
   */
  public String getTagName() {
    return tagName;
  }

  /**
   * Sets the name of the tag.
   *
   * @param tagName the name of the tag
   */
  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  /**
   * Returns a string representation of the Tag object.
   *
   * @return the string representation
   */
  @Override
  public String toString() {
    return "Tag{" +
        "tagId=" + tagId +
        ", tagName='" + tagName + '\'' +
        '}';
  }
}
