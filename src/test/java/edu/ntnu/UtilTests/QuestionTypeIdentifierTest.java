package edu.ntnu.UtilTests;
import edu.ntnu.dao.questions.*;
import edu.ntnu.dto.questions.*;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.utils.QuestionTypeIdentifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTypeIdentifierTest {

  @Test
  void testIdentifyQuestionType() {
    QuestionType type1 = QuestionTypeIdentifier.identifyQuestionType(new MultipleChoiceQuestionDAO());
    assertEquals(QuestionType.MULTIPLE_CHOICE, type1);

    QuestionType type2 = QuestionTypeIdentifier.identifyQuestionType(new TextInputQuestionDAO());
    assertEquals(QuestionType.TEXT_INPUT, type2);

    QuestionType type3 = QuestionTypeIdentifier.identifyQuestionType(new DragDropQuestionDAO());
    assertEquals(QuestionType.DRAG_AND_DROP, type3);

    QuestionType type4 = QuestionTypeIdentifier.identifyQuestionType(new TrueFalseQuestionDAO());
    assertEquals(QuestionType.TRUE_FALSE, type4);

    assertThrows(IllegalArgumentException.class, () -> QuestionTypeIdentifier.identifyQuestionType(new UnknownQuestionDAO()));
  }

  @Test
  void testIdentifyQuestionDTOType() {
    QuestionType type1 = QuestionTypeIdentifier.identifyQuestionDTOType(new MultipleChoiceQuestionDTO());
    assertEquals(QuestionType.MULTIPLE_CHOICE, type1);

    QuestionType type2 = QuestionTypeIdentifier.identifyQuestionDTOType(new TextInputQuestionDTO());
    assertEquals(QuestionType.TEXT_INPUT, type2);

    QuestionType type3 = QuestionTypeIdentifier.identifyQuestionDTOType(new DragDropQuestionDTO());
    assertEquals(QuestionType.DRAG_AND_DROP, type3);

    QuestionType type4 = QuestionTypeIdentifier.identifyQuestionDTOType(new TrueFalseQuestionDTO());
    assertEquals(QuestionType.TRUE_FALSE, type4);

    assertThrows(IllegalArgumentException.class, () -> QuestionTypeIdentifier.identifyQuestionDTOType(new UnknownQuestionDTO()));
  }

  // Dummy classes for testing
  private static class UnknownQuestionDAO extends QuestionDAO {
  }

  private static class UnknownQuestionDTO extends QuestionDTO {
  }
}