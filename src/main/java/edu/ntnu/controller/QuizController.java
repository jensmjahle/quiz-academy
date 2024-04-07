package edu.ntnu.controller;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.TagDTO;
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

/**
 * Controller for handling quiz requests.
 */
@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {
  private final Logger logger = Logger.getLogger(QuizController.class.getName());
  private final QuizService quizService;

  /**
   * Creates a new instance of the QuizController class.
   *
   * @param quizService the quiz service
   */
  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  /**
   * Get a quiz by id.
   *
   * @param quizId the id of the quiz
   * @return the quiz with the given id
   */
 @GetMapping("/{quizId}")
  public ResponseEntity<QuizDTO> getQuiz(@PathVariable Long quizId) {
    logger.info("Received request to get quiz with id: " + quizId + ".");
    return quizService.getQuiz(quizId);
  }

  /**
   * Get all quizzes for a user.
   *
   * @param username the username of the user
   * @return all quizzes for the user
   */
  @GetMapping("/all/{username}")
  public ResponseEntity<Iterable<QuizDTO>> getAllQuizzes(@PathVariable String username) {
      logger.info("Received request to get all quizzes for user: " + username + ".");
      return quizService.getAllQuizzes(username);
  }

  /**
   * Delete a quiz by id.
   *
   * @param quizId the id of the quiz
   * @return the response entity
   */
  @DeleteMapping("/delete/{quizId}")
  public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
    logger.info("Received request to delete quiz with id: " + quizId + ".");
    return quizService.deleteQuiz(quizId);
  }

  /**
   * Create a new quiz.
   *
   * @param quizDTO the quiz to create
   * @return the response entity
   */
  @PostMapping("/create")
  public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) {
    logger.info("Received request to create quiz with title: " + quizDTO.getQuizName() + ".");
    return quizService.createQuiz(quizDTO);
  }

  /**
   * Get all public quizzes.
   * @return all public quizzes
   */
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

  @GetMapping("public/all/tags/{tagId}")
  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzesByTag(@PathVariable Long tagId) {
    logger.info("Received request to get all quizzes with tag: " + tagId + ".");
    return quizService.getAllPublicQuizzesByTag(tagId);
  }

  @GetMapping("public/all/{searchString}")
  public ResponseEntity<Iterable<QuizDTO>> getAllPublicQuizzesBySearchString(@PathVariable String searchString) {
    logger.info("Received request to get all quizzes with search string: " + searchString + ".");
    return quizService.getAllPublicQuizzesBySearchString(searchString);
  }


}
