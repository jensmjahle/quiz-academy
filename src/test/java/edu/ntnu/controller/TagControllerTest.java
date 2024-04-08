package edu.ntnu.controller;

import edu.ntnu.dto.TagDTO;
import edu.ntnu.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TagControllerTest {

  @Mock
  private TagService tagService;

  @InjectMocks
  private TagController tagController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
  }

  @Test
  void testGetAllTags() throws Exception {
    TagDTO tag1 = new TagDTO();
    tag1.setTagId(1L);
    tag1.setTagName("Tag1");

    TagDTO tag2 = new TagDTO();
    tag2.setTagId(2L);
    tag2.setTagName("Tag2");

    List<TagDTO> tags = Arrays.asList(tag1, tag2);

    when(tagService.getAllTags()).thenReturn(ResponseEntity.ok(tags));

    mockMvc.perform(get("/tag/all")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}