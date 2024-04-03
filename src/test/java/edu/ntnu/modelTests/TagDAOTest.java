package edu.ntnu.modelTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.dao.TagDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tag Tests")
public class TagDAOTest {

  @Test
  @DisplayName("Tag is correctly instantiated")
  public void testTagInstantiation() {
    // Arrange
    String tagName = "Test Tag";

    // Act
    TagDAO tagDAO = new TagDAO(tagName);

    // Assert
    assertThat(tagDAO).isNotNull();
    assertThat(tagDAO.getTagName()).isEqualTo(tagName);
  }

  @Test
  @DisplayName("Tag setters and getters work correctly")
  public void testTagSettersAndGetters() {
    // Arrange
    TagDAO tagDAO = new TagDAO();
    String tagName = "Test Tag";

    // Act
    tagDAO.setTagName(tagName);

    // Assert
    assertThat(tagDAO.getTagName()).isEqualTo(tagName);
  }
}
