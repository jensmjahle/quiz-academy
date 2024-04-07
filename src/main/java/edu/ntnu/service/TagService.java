package edu.ntnu.service;

import edu.ntnu.dao.TagDAO;
import edu.ntnu.dto.TagDTO;
import edu.ntnu.mapper.TagMapper;
import edu.ntnu.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TagService {
  Logger logger = Logger.getLogger(TagService.class.getName());

  TagRepository tagRepository;
  TagMapper tagMapper = new TagMapper();

  @Autowired
  public TagService(TagRepository tagRepository, TagMapper tagMapper) {
    this.tagRepository = tagRepository;
    this.tagMapper = tagMapper;
  }


  public ResponseEntity<Iterable<TagDTO>> getAllTags() {
    try {
      Iterable<TagDAO> tags = tagRepository.findAll();

      List<TagDTO> tagDTOs= new ArrayList<>();
      for (TagDAO tag : tags) {
        tagDTOs.add(tagMapper.toDTO(tag));
      }
      return ResponseEntity.ok(tagDTOs);
    } catch (Exception e) {
      logger.severe("Failed to get all tags: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

}
