package edu.ntnu.mapperTests;

import edu.ntnu.dao.TagDAO;
import edu.ntnu.dto.TagDTO;
import edu.ntnu.mapper.TagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagMapperTest {

  private TagMapper tagMapper;

  @BeforeEach
  void setUp() {
    tagMapper = new TagMapper();
  }

  @Test
  void testToTagDTO() {
    // Given
    TagDAO tagDAO = new TagDAO();
    tagDAO.setTagId(1L);
    tagDAO.setTagName("Sample Tag");

    // When
    TagDTO tagDTO = tagMapper.toTagDTO(tagDAO);

    // Then
    assertEquals(1L, tagDTO.getTagId());
    assertEquals("Sample Tag", tagDTO.getTagName());
  }

  @Test
  void testToDAO() {
    // Given
    TagDTO tagDTO = new TagDTO();
    tagDTO.setTagId(1L);
    tagDTO.setTagName("Sample Tag");

    // When
    TagDAO tagDAO = tagMapper.toDAO(tagDTO);

    // Then
    assertEquals(1L, tagDAO.getTagId());
    assertEquals("Sample Tag", tagDAO.getTagName());
  }

  @Test
  void testToDAOWithoutId() {
    // Given
    TagDTO tagDTO = new TagDTO();
    tagDTO.setTagName("Sample Tag");

    // When
    TagDAO tagDAO = tagMapper.toDAOWithoutId(tagDTO);

    // Then
    assertEquals("Sample Tag", tagDAO.getTagName());
  }
}
