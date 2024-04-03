package edu.ntnu.dao.questions;

import edu.ntnu.dao.QuizDAO;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

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




