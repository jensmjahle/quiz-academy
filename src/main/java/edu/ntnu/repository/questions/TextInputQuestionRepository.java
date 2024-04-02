package edu.ntnu.repository.questions;

import edu.ntnu.model.questions.TextInputQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for the TextInputQuestion entity.
 */
@Repository
public interface TextInputQuestionRepository extends QuestionRepository {

Iterable<TextInputQuestion> findAllByQuiz_QuizId(Long quizId);

TextInputQuestion findByQuestionId(Long questionId);


}
