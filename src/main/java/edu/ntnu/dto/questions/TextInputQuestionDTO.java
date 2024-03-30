package edu.ntnu.dto.questions;

public class TextInputQuestionDTO extends QuestionDTO {
  private String answer;

  public TextInputQuestionDTO() {
  }

  public TextInputQuestionDTO(Long questionId, String questionText, Long quizId, String answer) {
    super(questionId, questionText, quizId);
    this.answer = answer;
  }


  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  @Override
  public String toString() {
    return "TextInputQuestionDTO{" +
        "answer='" + answer + '\'' +
        '}';
  }
}
