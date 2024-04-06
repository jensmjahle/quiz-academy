package edu.ntnu.controller;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.service.QuizService;
import java.util.logging.Logger;
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

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {
  private final Logger logger = Logger.getLogger(QuizController.class.getName());
  private final QuizService quizService;

  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

 @GetMapping("/{quizId}")
  public ResponseEntity<QuizDTO> getQuiz(@PathVariable Long quizId) {
    logger.info("Received request to get quiz with id: " + quizId + ".");
    return quizService.getQuiz(quizId);
  }

  @GetMapping("/all/{username}")
  public ResponseEntity<Iterable<QuizDTO>> getAllQuizzes(@PathVariable String username) {
    logger.info("Received request to get all quizzes for user: " + username + ".");
    return quizService.getAllQuizzes(username);
  }

  @DeleteMapping("/delete/{quizId}")
  public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
    logger.info("Received request to delete quiz with id: " + quizId + ".");
    return quizService.deleteQuiz(quizId);
  }

  @PostMapping("/create")
  public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) {
    logger.info("Received request to create quiz with title: " + quizDTO.getQuizName() + ".");
    return quizService.createQuiz(quizDTO);
  }
  @GetMapping("/all")
  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzes() {
      logger.info("Received request to get all quizzes.");
      return quizService.getAllPublicQuizzes();
  }

  @PostMapping("/update")
  public ResponseEntity<QuizDTO> updateQuiz(@RequestBody QuizDTO quizDTO) {
    logger.info("Received request to update quiz with id: " + quizDTO.getQuizId() + ".");
    return quizService.updateQuiz(quizDTO);
    }
}
