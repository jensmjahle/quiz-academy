package edu.ntnu.dao.questions;

import edu.ntnu.dao.QuizDAO;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


/**
 * Represents a question in the system. A question is a part of a quiz that a user can answer.
 * This is an abstract class that is extended by different types of questions.
 */
@MappedSuperclass
public abstract class QuestionDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionIdGenerator")
  @SequenceGenerator(name = "questionIdGenerator", sequenceName = "question_sequence", allocationSize = 1)
  private Long questionId;

  @Column(nullable = false)
  private String questionText;
  @ManyToOne
  @JoinColumn(name = "quizId")
  private QuizDAO quizDAO;

  @Lob
  @Column(name = "image", nullable = true)
  private byte[] questionImage;

  public QuestionDAO() {
  }

  public QuestionDAO(Long questionId, String questionText, Long quizId) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.quizDAO = new QuizDAO();
    this.quizDAO.setQuizId(quizId);
  }


  public void setQuestionText(String sampleMultipleChoiceQuestion) {
    this.questionText = sampleMultipleChoiceQuestion;
  }

  public String getQuestionText() {
    return questionText;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public Long getQuizId() {
    return quizDAO.getQuizId();
  }
  public void setQuizId(Long quizId) {
    this.quizDAO = new QuizDAO();
    this.quizDAO.setQuizId(quizId);
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public byte[] getImage() {
    return questionImage;
  }

  public void setImage(byte[] image) {
    this.questionImage = image;
  }
}




