package edu.ntnu.model.questions;

import edu.ntnu.model.Quiz;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Represents a question in the system. A question is a part of a quiz that a user can answer.
 * This is an abstract class that is extended by different types of questions.
 */
@MappedSuperclass
public abstract class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long questionId;

  @Column(nullable = false)
  private String questionText;
  @ManyToOne
  @JoinColumn(name = "quizId")
  private Quiz quiz;

  public Question() {
  }

  public Question(Long questionId, String questionText, Long quizId) {
    this.questionId = questionId;
    this.questionText = questionText;
    this.quiz = new Quiz();
    this.quiz.setQuizId(quizId);
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
    return quiz.getQuizId();
  }
  public void setQuizId(Long quizId) {
    this.quiz.setQuizId(quizId);
  }
}




