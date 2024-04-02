package edu.ntnu.dto;

public class TagDTO {
  private Long tagId;
  private String tagName;

  public TagDTO() {
  }

  public TagDTO(Long tagId, String tagName) {
    this.tagId = tagId;
    this.tagName = tagName;
  }

  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

}
