package edu.ntnu.repository;

import edu.ntnu.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Quiz entity.
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

  Quiz findByQuizId(Long quizId);

  Quiz findByQuizName(String quizName);


}
