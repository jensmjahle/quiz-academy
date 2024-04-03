package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface DragDropQuestionDAORepository extends QuestionDAORepository {
  Iterable<DragDropQuestionDAO> findAllByQuiz_QuizId(Long quizId);

  DragDropQuestionDAO findByQuestionId(Long questionId);

}
