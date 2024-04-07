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

  /**
   * Creates a new instance of the Question class.
   */
  public QuestionDAO() {
  }

  /**
   * Creates a new instance of the Question class.
   *
   * @param questionId the id of the question
   * @param questionText the text of the question
   * @param quizId the id of the quiz
   */
  public QuestionDAO(Long questionId, String questionText, Long quizId) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.quizDAO = new QuizDAO();
    this.quizDAO.setQuizId(quizId);
  }

  /**
   * Sets the text of the question.
   *
   * @param sampleMultipleChoiceQuestion the text of the question
   */
  public void setQuestionText(String sampleMultipleChoiceQuestion) {
    this.questionText = sampleMultipleChoiceQuestion;
  }

  /**
   * Returns the text of the question.
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * Returns the id of the question.
   */
  public Long getQuestionId() {
    return questionId;
  }

  /**
   * Gets the quiz id of the quiz.
   */
  public Long getQuizId() {
    return quizDAO.getQuizId();
  }

  /**
   * Sets the quiz id of the quiz.
   * @param quizId the id of the quiz
   */
  public void setQuizId(Long quizId) {
    this.quizDAO = new QuizDAO();
    this.quizDAO.setQuizId(quizId);
  }

  /**
   * Sets the question id.
   * @param questionId the question id
   */
  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  /**
   * Gets the image of the question.
   * @return the image of the question
   */
  public byte[] getImage() {
    return questionImage;
  }

  /**
   * Sets the image of the question.
   * @param image the image of the question
   */
  public void setImage(byte[] image) {
    this.questionImage = image;
  }
}




