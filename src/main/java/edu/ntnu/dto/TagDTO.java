package edu.ntnu.dto;

/**
 * Data transfer object for the Tag entity.
 */
public class TagDTO {
  private Long tagId;
  private String tagName;

  /**
   * Constructor for the TagDTO class.
   */
  public TagDTO() {
  }

  /**
   * Constructor for the TagDTO class.
   * @param tagId the id of the tag
   * @param tagName the name of the tag
   */
  public TagDTO(Long tagId, String tagName) {
    this.tagId = tagId;
    this.tagName = tagName;
  }

  /**
   * Getter for the tag id.
   * @return the tag id
   */
  public Long getTagId() {
    return tagId;
  }

  /**
   * Setter for the tag id.
   * @param tagId the tag id
   */
  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  /**
   * Getter for the tag name.
   * @return the tag name
   */
  public String getTagName() {
    return tagName;
  }

  /**
   * Setter for the tag name.
   * @param tagName the tag name
   */
  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  /**
   * Returns a string representation of the TagDTO object.
   * @return the string representation
   */
  @Override
  public String toString() {
    return "TagDTO{" +
        "tagId=" + tagId +
        ", tagName='" + tagName + '\'' +
        '}';
  }
}
