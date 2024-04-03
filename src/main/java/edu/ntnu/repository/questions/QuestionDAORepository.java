package edu.ntnu.repository.questions;

import edu.ntnu.dao.questions.QuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repository for the Question entity.
 */
@NoRepositoryBean
public interface QuestionDAORepository extends JpaRepository<QuestionDAO, Long> {


}
