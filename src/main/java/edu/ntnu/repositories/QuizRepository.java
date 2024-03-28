package edu.ntnu.repositories;

import edu.ntnu.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

  Quiz findByQuizId(Long quizId);

  Quiz findByQuizName(String quizName);


}
