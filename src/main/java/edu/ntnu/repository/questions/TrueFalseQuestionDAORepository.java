package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrueFalseQuestionDAORepository extends JpaRepository<TrueFalseQuestionDAO, Long> {
  Iterable<TrueFalseQuestionDAO> findAllByQuizDAO_QuizId(Long quizId);

  TrueFalseQuestionDAO findByQuestionId(Long questionId);


}
