package edu.ntnu.utils;

import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.Question;
import edu.ntnu.model.questions.TextInputQuestion;

public class QuestionTypeIdentifier {

  public static QuestionType identifyQuestionType(Question question) {
    if (question instanceof MultipleChoiceQuestion) {
      return QuestionType.MULTIPLE_CHOICE;
    } else if (question instanceof TextInputQuestion) {
      return QuestionType.TEXT_INPUT;
    } else {
      throw new IllegalArgumentException("Unknown question type");
    }
  }

  public static QuestionType identifyQuestionDTOType(QuestionDTO questionDTO) {
    if (questionDTO instanceof MultipleChoiceQuestionDTO) {
      return QuestionType.MULTIPLE_CHOICE;
    } else if (questionDTO instanceof TextInputQuestionDTO) {
      return QuestionType.TEXT_INPUT;
    } else {
      throw new IllegalArgumentException("Unknown question type");
    }
  }

  public static QuestionType indentifyQuestionTypeFromString(String questionType) {
    switch (questionType) {
      case "MULTIPLE_CHOICE":
        return QuestionType.MULTIPLE_CHOICE;
      case "TEXT_INPUT":
        return QuestionType.TEXT_INPUT;
      default:
        throw new IllegalArgumentException("Unknown question type");
    }
  }


}
