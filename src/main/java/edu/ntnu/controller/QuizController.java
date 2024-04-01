package edu.ntnu.controller;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

  private final QuizService quizService;

  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

 @GetMapping("/{quizId}")
  public ResponseEntity<QuizDTO> getQuiz(@PathVariable Long quizId) {
    return quizService.getQuiz(quizId);
  }

  @GetMapping("/all/{username}")
  public ResponseEntity<Iterable<QuizDTO>> getAllQuizzes(@PathVariable String username) {
    return quizService.getAllQuizzes(username);
  }



}
