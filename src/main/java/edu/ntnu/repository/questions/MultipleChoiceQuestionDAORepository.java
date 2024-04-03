package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import org.springframework.stereotype.Repository;

/**
 * Repository for the TextInputQuestion entity.
 */
@Repository
public interface MultipleChoiceQuestionDAORepository extends QuestionDAORepository {

Iterable<MultipleChoiceQuestionDAO> findAllByQuizDAO_QuizId(Long quizId);

MultipleChoiceQuestionDAO findByQuestionId(Long questionId);



}
