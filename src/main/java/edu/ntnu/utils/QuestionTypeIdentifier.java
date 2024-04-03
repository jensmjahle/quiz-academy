package edu.ntnu.utils;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dao.questions.TrueFalseQuestionDAO;
import edu.ntnu.dto.questions.DragDropQuestionDTO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.dto.questions.TrueFalseQuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.dao.questions.QuestionDAO;
import edu.ntnu.dao.questions.TextInputQuestionDAO;

public class QuestionTypeIdentifier {

  public static QuestionType identifyQuestionType(QuestionDAO questionDAO) {
    if (questionDAO instanceof MultipleChoiceQuestionDAO) {
      return QuestionType.MULTIPLE_CHOICE;
    } else if (questionDAO instanceof TextInputQuestionDAO) {
      return QuestionType.TEXT_INPUT;
    } else if (questionDAO instanceof DragDropQuestionDAO) {
      return QuestionType.DRAG_AND_DROP;
    } else if (questionDAO instanceof TrueFalseQuestionDAO) {
      return QuestionType.TRUE_FALSE;
    } else {
      throw new IllegalArgumentException("Unknown question type");
    }
  }

  public static QuestionType identifyQuestionDTOType(QuestionDTO questionDTO) {
    if (questionDTO instanceof MultipleChoiceQuestionDTO) {
      return QuestionType.MULTIPLE_CHOICE;
    } else if (questionDTO instanceof TextInputQuestionDTO) {
      return QuestionType.TEXT_INPUT;
    } else if (questionDTO instanceof DragDropQuestionDTO) {
      return QuestionType.DRAG_AND_DROP;
    } else if (questionDTO instanceof TrueFalseQuestionDTO) {
      return QuestionType.TRUE_FALSE;
    } else {
      throw new IllegalArgumentException("Unknown question type");
    }
  }

}
