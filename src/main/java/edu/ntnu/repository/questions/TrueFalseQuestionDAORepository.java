package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface TrueFalseQuestionDAORepository extends QuestionDAORepository {
  Iterable<TrueFalseQuestionDAO> findAllByQuizDAO_QuizId(Long quizId);

  TrueFalseQuestionDAO findByQuestionId(Long questionId);


}
