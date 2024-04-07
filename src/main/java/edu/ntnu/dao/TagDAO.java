package edu.ntnu.dao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import edu.ntnu.dao.QuizDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


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
