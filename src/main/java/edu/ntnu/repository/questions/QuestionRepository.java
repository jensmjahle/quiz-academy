package edu.ntnu.repository.questions;

import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repository for the Question entity.
 */
@NoRepositoryBean
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
