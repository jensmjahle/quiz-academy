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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;


@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {
  private final Logger logger = Logger.getLogger(QuestionController.class.getName());
  private final QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/{questionId}")
  public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long questionId) {
    logger.info("Received request to get question with id: " + questionId + ".");
    return questionService.getQuestion(questionId);
  }

  @PostMapping("/create")
  public ResponseEntity<QuestionDTO> createQuestion(QuestionDTO questionDTO) {
    logger.info("Received request to create question: " + questionDTO.getQuestionText() + ".");
    return questionService.createQuestion(questionDTO);
  }

  @PostMapping("/update/{questionId}")
  public ResponseEntity<String> updateQuestion(@PathVariable Long questionId, QuestionDTO questionDTO) {
    logger.info("Received request to update question with id: " + questionId + ".");
    return questionService.updateQuestion(questionId, questionDTO);
  }


  @DeleteMapping("/delete/{questionId}")
  public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId, @RequestParam String questionType) {
    logger.info("Received request to delete question with id: " + questionId + " and type: " + questionType + ".");
    return questionService.deleteQuestion(questionId, questionType);
  }

}
