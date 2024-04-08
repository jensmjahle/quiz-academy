package edu.ntnu.servicesTest;

import edu.ntnu.dao.TagDAO;
import edu.ntnu.dto.TagDTO;
import edu.ntnu.mapper.TagMapper;
import edu.ntnu.repository.TagRepository;
import edu.ntnu.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TagServiceTest {

  @Mock
  private TagRepository tagRepository;

  @Mock
  private TagMapper tagMapper;

  @InjectMocks
  private TagService tagService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllTags() {
    TagDAO tagDAO = new TagDAO();
    tagDAO.setTagName("tag1");
    TagDTO tagDTO = new TagDTO();
    tagDTO.setTagName("tag1");

    when(tagRepository.findAll()).thenReturn(Arrays.asList(tagDAO));
    when(tagMapper.toTagDTO(tagDAO)).thenReturn(tagDTO);

    ResponseEntity<Iterable<TagDTO>> responseEntity = tagService.getAllTags();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    List<TagDTO> body = (List<TagDTO>) responseEntity.getBody();
    assertEquals(1, body.size());
    assertEquals("tag1", body.get(0).getTagName());
  }
}