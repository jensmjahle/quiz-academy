package edu.ntnu.dto.questions;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestionDTO extends QuestionDTO {
  private List<String> alternatives;
  private List<String> correctAlternatives;

  public MultipleChoiceQuestionDTO() {
  }

  public MultipleChoiceQuestionDTO(Long questionId, String questionText, Long quizId, List<String> alternatives, List<String> correctAlternatives) {
    super(questionId, questionText, quizId);
    this.alternatives = alternatives;
    this.correctAlternatives = correctAlternatives;
  }

  public List<String> getAlternatives() {
    return alternatives;
  }

  public void setAlternatives(List<String> alternatives) {
    this.alternatives = alternatives;
  }

  public List<String> getCorrectAlternatives() {
    return correctAlternatives;
  }

  public void setCorrectAlternatives(List<String> correctAlternatives) {
    this.correctAlternatives = correctAlternatives;
  }
}
