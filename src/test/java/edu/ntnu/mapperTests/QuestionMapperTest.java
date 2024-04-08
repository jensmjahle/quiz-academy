package edu.ntnu.mapperTests;

import edu.ntnu.dao.questions.DragDropQuestionDAO;
import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dao.questions.QuestionDAO;
import edu.ntnu.dto.questions.DragDropQuestionDTO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.mapper.QuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionMapperTest {

  private QuestionMapper questionMapper;

  @BeforeEach
  void setUp() {
    questionMapper = new QuestionMapper();
  }

  @Test
  void testToDTOMultipleChoiceQuestion() {
    // Given
    MultipleChoiceQuestionDAO mockMCQDAO = new MultipleChoiceQuestionDAO();
    mockMCQDAO.setQuestionId(1L);
    mockMCQDAO.setQuestionText("Sample question");
    mockMCQDAO.setQuizId(1L);
    mockMCQDAO.setAlternatives("Option1*Option2*Option3");
    mockMCQDAO.setCorrectAlternatives("Option1");

    // When
    QuestionDTO resultDTO = questionMapper.toDTO(mockMCQDAO);

    // Then
    assertTrue(resultDTO instanceof MultipleChoiceQuestionDTO);
    MultipleChoiceQuestionDTO mcqDTO = (MultipleChoiceQuestionDTO) resultDTO;
    assertEquals(1L, mcqDTO.getQuestionId());
    assertEquals("Sample question", mcqDTO.getQuestionText());
    assertEquals(1L, mcqDTO.getQuizId());
    assertEquals(QuestionType.MULTIPLE_CHOICE.toString(), mcqDTO.getType());
    assertEquals(Arrays.asList("Option1", "Option2", "Option3"), mcqDTO.getAlternatives());
    assertEquals(Arrays.asList("Option1"), mcqDTO.getCorrectAlternatives());
  }

  @Test
  void testToDAOMultipleChoiceQuestionDTO() {
    // Given
    MultipleChoiceQuestionDTO mockMCQDTO = new MultipleChoiceQuestionDTO();
    mockMCQDTO.setQuestionId(1L);
    mockMCQDTO.setQuestionText("Sample question");
    mockMCQDTO.setQuizId(1L);
    mockMCQDTO.setType(String.valueOf(QuestionType.MULTIPLE_CHOICE)); // Set enum directly
    mockMCQDTO.setAlternatives(Arrays.asList("Option1", "Option2", "Option3"));
    mockMCQDTO.setCorrectAlternatives(Arrays.asList("Option1"));

    // When
    QuestionDAO resultDAO = questionMapper.toDAO(mockMCQDTO);

    // Then
    assertTrue(resultDAO instanceof MultipleChoiceQuestionDAO);
    MultipleChoiceQuestionDAO mcqDAO = (MultipleChoiceQuestionDAO) resultDAO;
    assertEquals(1L, mcqDAO.getQuestionId());
    assertEquals("Sample question", mcqDAO.getQuestionText());
    assertEquals(1L, mcqDAO.getQuizId());
    assertEquals("Option1*Option2*Option3", mcqDAO.getAlternatives());
    assertEquals("Option1", mcqDAO.getCorrectAlternatives());
  }

  @Test
  void testToDTODragDropQuestion() {
    // Given
    DragDropQuestionDAO mockDDQDAO = new DragDropQuestionDAO();
    mockDDQDAO.setQuestionId(1L);
    mockDDQDAO.setQuestionText("Sample question");
    mockDDQDAO.setQuizId(1L);
    mockDDQDAO.setCategories("Category1<Item1*Item2;Category2<Item3*Item4");

    // When
    QuestionDTO resultDTO = questionMapper.toDTO(mockDDQDAO);

    // Then
    assertTrue(resultDTO instanceof DragDropQuestionDTO);
    DragDropQuestionDTO ddqDTO = (DragDropQuestionDTO) resultDTO;
    assertEquals(1L, ddqDTO.getQuestionId());
    assertEquals("Sample question", ddqDTO.getQuestionText());
    assertEquals(1L, ddqDTO.getQuizId());
    assertEquals(QuestionType.DRAG_AND_DROP.toString(), ddqDTO.getType());
    Map<String, List<String>> expectedCategories = new HashMap<>();
    expectedCategories.put("Category1", Arrays.asList("Item1", "Item2"));
    expectedCategories.put("Category2", Arrays.asList("Item3", "Item4"));
    assertEquals(expectedCategories, ddqDTO.getCategories());
  }

  // Add tests for other question types and methods
}
