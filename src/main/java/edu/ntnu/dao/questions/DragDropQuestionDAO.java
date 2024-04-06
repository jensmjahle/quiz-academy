package edu.ntnu.dao.questions;

import edu.ntnu.dao.QuizDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
@Entity
public class DragDropQuestionDAO extends QuestionDAO {

  //CategoryName seperated from items by '<'
  //CategoryItem seperated by '*': "cat*dog*bird"
  //Categories seperated by ';'
  //Example: "Animals<cat*dog*bird;Fruits<apple*banana*orange"
  @Column(nullable = false)
  private String categories;

  public DragDropQuestionDAO() {
  }

  public DragDropQuestionDAO(Long questionId, String questionText, Long quizId, String categories) {
    super(questionId, questionText, quizId);
    this.categories = categories;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }
}
