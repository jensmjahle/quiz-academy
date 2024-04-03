package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DragDropQuestionDAORepository extends JpaRepository<DragDropQuestionDAO, Long> {
  Iterable<DragDropQuestionDAO> findAllByQuizDAO_QuizId(Long quizId);
  DragDropQuestionDAO findByQuestionId(Long questionId);

}
