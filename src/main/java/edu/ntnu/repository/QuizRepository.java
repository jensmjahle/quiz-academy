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

  Iterable<QuizDAO> findAllByIsPublic(boolean isPublic);
  @Modifying
  @Transactional
  @Query("DELETE FROM QuizDAO q WHERE q.quizId = :quizId")
  void deleteByQuizId(Long quizId);

  Iterable<QuizDAO> findAllByIsPublicAndTagDAOs_TagId(boolean b, Long tagId);


  @Query("SELECT q FROM QuizDAO q LEFT JOIN q.tagDAOs t WHERE q.isPublic = true AND (LOWER(q.quizName) LIKE %:searchString% OR LOWER(t.tagName) LIKE %:searchString%)")
  Iterable<QuizDAO> findAllPublicQuizzesWithSearchString(String searchString);
}
