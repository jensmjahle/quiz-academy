package edu.ntnu.dto.questions;

public class MultipleChoiceQuestionDTO extends QuestionDTO {
  private String alternative1;
  private String alternative2;
  private String alternative3;
  private String alternative4;
  private String correctAlternatives;

  public MultipleChoiceQuestionDTO() {
  }

  public MultipleChoiceQuestionDTO(Long questionId, String questionText, Long quizId, String alternative1, String alternative2, String alternative3, String alternative4, String correctAlternatives) {
    super(questionId, questionText, quizId);
    this.alternative1 = alternative1;
    this.alternative2 = alternative2;
    this.alternative3 = alternative3;
    this.alternative4 = alternative4;
    this.correctAlternatives = correctAlternatives;
  }


  public String getAlternative1() {
    return alternative1;
  }

  public void setAlternative1(String alternative1) {
    this.alternative1 = alternative1;
  }

  public String getAlternative2() {
    return alternative2;
  }

  public void setAlternative2(String alternative2) {
    this.alternative2 = alternative2;
  }

  public String getAlternative3() {
    return alternative3;
  }

  public void setAlternative3(String alternative3) {
    this.alternative3 = alternative3;
  }

  public String getAlternative4() {
    return alternative4;
  }

  public void setAlternative4(String alternative4) {
    this.alternative4 = alternative4;
  }

  public String getCorrectAlternatives() {
    return correctAlternatives;
  }

  public void setCorrectAlternatives(String correctAlternatives) {
    this.correctAlternatives = correctAlternatives;
  }

  @Override
  public String toString() {
    return "MultipleChoiceQuestionDTO{" +
        "alternative1='" + alternative1 + '\'' +
        ", alternative2='" + alternative2 + '\'' +
        ", alternative3='" + alternative3 + '\'' +
        ", alternative4='" + alternative4 + '\'' +
        ", correctAlternatives='" + correctAlternatives + '\'' +
        '}';
  }
}
