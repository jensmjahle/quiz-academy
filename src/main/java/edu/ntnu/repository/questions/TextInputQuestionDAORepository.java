package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.TextInputQuestionDAO;
import org.springframework.stereotype.Repository;

/**
 * Repository for the TextInputQuestion entity.
 */
@Repository
public interface TextInputQuestionDAORepository extends QuestionDAORepository {

Iterable<TextInputQuestionDAO> findAllByQuiz_QuizId(Long quizId);

TextInputQuestionDAO findByQuestionId(Long questionId);


}
