package edu.ntnu.repository;

import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.TagDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository for the Quiz entity.
 */
@Repository
public interface QuizRepository extends JpaRepository<QuizDAO, Long> {

  QuizDAO findByQuizId(Long quizId);

  QuizDAO findByQuizName(String quizName);

  Iterable<QuizDAO> findAllByUserDAO_Username(String username);

  @Query("SELECT q.tagDAOs FROM QuizDAO q WHERE q.quizId = :quizId")
  Iterable<TagDAO> findAllTagsByQuizId(Long quizId);

  @Modifying
  @Transactional
  @Query("DELETE FROM QuizDAO q WHERE q.quizId = :quizId")
  void deleteByQuizId(Long quizId);
/*
  @Transactional
  @Modifying
  @Query("UPDATE Quiz q SET q.tags.add(:tagId) WHERE q.quizId = :quizId")
  void addTag(Long quizId, Long tagId);

*/

}
