package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.TextInputQuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the TextInputQuestion entity.
 */
@Repository
public interface TextInputQuestionDAORepository extends JpaRepository<TextInputQuestionDAO, Long> {

Iterable<TextInputQuestionDAO> findAllByQuizDAO_QuizId(Long quizId);

TextInputQuestionDAO findByQuestionId(Long questionId);


}
