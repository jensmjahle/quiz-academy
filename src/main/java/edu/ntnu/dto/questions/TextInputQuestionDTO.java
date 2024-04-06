package edu.ntnu.dto.questions;

import java.util.List;

public class TextInputQuestionDTO extends QuestionDTO {
  private List<String> answers;

  public TextInputQuestionDTO() {
  }

  public TextInputQuestionDTO(Long questionId, String questionText, Long quizId, String type, List<String> answers) {
    super(questionId, questionText, quizId, type);
    this.answers = answers;
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }
}
