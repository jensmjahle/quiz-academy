package edu.ntnu.controller;

import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Controller for handling question requests.
 */
@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {
  private final Logger logger = Logger.getLogger(QuestionController.class.getName());
  private final QuestionService questionService;

  /**
   * Creates a new instance of the QuestionController class.
   * @param questionService the question service
   */
  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  /**
   * Get a question by id.
   * @param questionId the id of the question
   * @return the question with the given id
   */
  @GetMapping("/{questionId}")
  public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long questionId) {
    logger.info("Received request to get question with id: " + questionId + ".");
    return questionService.getQuestion(questionId);
  }

  /**
   * Create a new question.
   * @param questionDTO the question to create
   * @return the response entity
   */
  @PostMapping("/create")
  public ResponseEntity<QuestionDTO> createQuestion(QuestionDTO questionDTO) {
    logger.info("Received request to create question: " + questionDTO.getQuestionText() + ".");
    return questionService.createQuestion(questionDTO);
  }

  /**
   * Update a question.
   * @param questionDTO the question to update
   * @return the response entity
   */
  @PostMapping("/update/{questionId}")
  public ResponseEntity<String> updateQuestion(@RequestBody QuestionDTO questionDTO) {
    logger.info("Received request to update question with id: " + questionDTO.getQuestionId() + ".");
    return questionService.updateQuestion(questionDTO);
  }

  /**
   * Delete a question by id.
   * @param questionId the id of the question
   * @return the response entity
   */
  @DeleteMapping("/delete/{questionId}")
  public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
    logger.info("Received request to delete question with id: " + questionId + ".");
    return questionService.deleteQuestion(questionId);
  }

}
