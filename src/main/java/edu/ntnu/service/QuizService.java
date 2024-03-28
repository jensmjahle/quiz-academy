package edu.ntnu.service;

import edu.ntnu.model.Quiz;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import edu.ntnu.repository.QuizRepository;

@Service
public class QuizService {

  private final QuizRepository quizRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  private final Logger logger = Logger.getLogger(QuizService.class.getName());

  @Autowired
  public QuizService(QuizRepository quizRepository) {
    this.quizRepository = quizRepository;
  }

  public ResponseEntity<Quiz> getQuiz(Long quizId) {
   try {
    logger.info("Received request for quiz with id: " + quizId);
    Quiz quiz = quizRepository.findByQuizId(quizId);
    if (quiz != null) {
      logger.info("Quiz with id " + quizId + " found. Returning quiz.");
      return ResponseEntity.ok(quiz);
    } else {
      logger.info("Quiz with id " + quizId + " not found.");
      return ResponseEntity.notFound().build();
    }
  } catch (Exception e) {
    logger.severe("An error occurred while getting quiz with id " + quizId + ": " + e.getMessage());
    return ResponseEntity.status(500).build();
  }
  }
}
