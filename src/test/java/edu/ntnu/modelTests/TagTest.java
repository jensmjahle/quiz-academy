package edu.ntnu.modelTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.model.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tag Tests")
public class TagTest {

  @Test
  @DisplayName("Tag is correctly instantiated")
  public void testTagInstantiation() {
    // Arrange
    String tagName = "Test Tag";

    // Act
    Tag tag = new Tag(tagName);

    // Assert
    assertThat(tag).isNotNull();
    assertThat(tag.getTagName()).isEqualTo(tagName);
  }

  @Test
  @DisplayName("Tag setters and getters work correctly")
  public void testTagSettersAndGetters() {
    // Arrange
    Tag tag = new Tag();
    String tagName = "Test Tag";

    // Act
    tag.setTagName(tagName);

    // Assert
    assertThat(tag.getTagName()).isEqualTo(tagName);
  }
}
