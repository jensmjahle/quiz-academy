package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository for the TextInputQuestion entity.
 */
@Repository
public interface MultipleChoiceQuestionDAORepository extends
    JpaRepository<MultipleChoiceQuestionDAO, Long> {
Iterable<MultipleChoiceQuestionDAO> findAllByQuizDAO_QuizId(Long quizId);

MultipleChoiceQuestionDAO findByQuestionId(Long questionId);



}
