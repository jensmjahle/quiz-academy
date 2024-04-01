package edu.ntnu.repository;

import edu.ntnu.model.Quiz;
import edu.ntnu.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository for the Quiz entity.
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

  Quiz findByQuizId(Long quizId);

  Quiz findByQuizName(String quizName);

  Iterable<Quiz> findAllByUser_Username(String username);

  @Query("SELECT q.tags FROM Quiz q WHERE q.quizId = :quizId")
  Iterable<Tag> findAllTagsByQuizId(Long quizId);
/*
  @Transactional
  @Modifying
  @Query("UPDATE Quiz q SET q.tags.add(:tagId) WHERE q.quizId = :quizId")
  void addTag(Long quizId, Long tagId);

*/

}
