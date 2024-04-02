package edu.ntnu.repositoryTests;

import edu.ntnu.model.Tag;
import edu.ntnu.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Tag Repository Tests")
public class TagRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TagRepository tagRepository;

  @Test
  @DisplayName("Tag is saved to the database correctly")
  public void testSaveTag() {
    // Create a tag
    Tag tag = new Tag("Test Tag");

    // Save the tag to the database
    Tag savedTag = tagRepository.save(tag);

    // Retrieve the tag from the database
    Tag retrievedTag = entityManager.find(Tag.class, savedTag.getTagId());

    // Check that the tag was saved correctly
    assertThat(retrievedTag.getTagName()).isEqualTo(tag.getTagName());
  }

  @Test
  @DisplayName("Tag is deleted from the database correctly")
  public void testDeleteTag() {
    // Create a tag
    Tag tag = new Tag("Test Tag");
    entityManager.persist(tag);

    // Delete the tag from the repository
    tagRepository.delete(tag);

    // Check that the tag was deleted successfully
    assertThat(tagRepository.findByTagName(tag.getTagName())).isNull();
  }

  @Test
  @DisplayName("Tag is retrieved from the database correctly")
  public void testGetTag() {
    // Create a tag
    Tag tag = new Tag("Test Tag");
    entityManager.persist(tag);

    // Retrieve the tag from the repository
    Tag retrievedTag = tagRepository.findByTagName(tag.getTagName());

    // Check that the tag was retrieved successfully
    assertThat(retrievedTag.getTagName()).isEqualTo(tag.getTagName());
  }
}
