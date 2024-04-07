package edu.ntnu.controller;

import edu.ntnu.dto.TagDTO;
import edu.ntnu.service.TagService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "http://localhost:5173")
public class TagController {
  Logger logger = Logger.getLogger(TagController.class.getName());
  TagService tagService;

  @Autowired
  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @GetMapping("/all")
  public ResponseEntity<Iterable<TagDTO>> getAllTags() {
    logger.info("Received request to get all tags.");
    return tagService.getAllTags();
  }

}
