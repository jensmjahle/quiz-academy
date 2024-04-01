package edu.ntnu.repository.questions;

import edu.ntnu.model.questions.MultipleChoiceQuestion;
import org.springframework.stereotype.Repository;

/**
 * Repository for the TextInputQuestion entity.
 */
@Repository
public interface MultipleChoiceQuestionRepository extends QuestionRepository {

Iterable<MultipleChoiceQuestion> findAllByQuiz_QuizId(Long quizId);

}
