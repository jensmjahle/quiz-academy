package edu.ntnu.repositoryTests;

import edu.ntnu.dao.TagDAO;
import edu.ntnu.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Tag Repository Tests")
public class TagDAORepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TagRepository tagRepository;

  @Test
  @DisplayName("Tag is saved to the database correctly")
  public void testSaveTag() {
    // Create a tag
    TagDAO tagDAO = new TagDAO("Test Tag");

    // Save the tag to the database
    TagDAO savedTagDAO = tagRepository.save(tagDAO);

    // Retrieve the tag from the database
    TagDAO retrievedTagDAO = entityManager.find(TagDAO.class, savedTagDAO.getTagId());

    // Check that the tag was saved correctly
    assertThat(retrievedTagDAO.getTagName()).isEqualTo(tagDAO.getTagName());
  }

  @Test
  @DisplayName("Tag is deleted from the database correctly")
  public void testDeleteTag() {
    // Create a tag
    TagDAO tagDAO = new TagDAO("Test Tag");
    entityManager.persist(tagDAO);

    // Delete the tag from the repository
    tagRepository.delete(tagDAO);

    // Check that the tag was deleted successfully
    assertThat(tagRepository.findByTagName(tagDAO.getTagName())).isNull();
  }

  @Test
  @DisplayName("Tag is retrieved from the database correctly")
  public void testGetTag() {
    // Create a tag
    TagDAO tagDAO = new TagDAO("Test Tag");
    entityManager.persist(tagDAO);

    // Retrieve the tag from the repository
    TagDAO retrievedTagDAO = tagRepository.findByTagName(tagDAO.getTagName());

    // Check that the tag was retrieved successfully
    assertThat(retrievedTagDAO.getTagName()).isEqualTo(tagDAO.getTagName());
  }
}
