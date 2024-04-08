package edu.ntnu.controller;

import edu.ntnu.dto.questions.DragDropQuestionDTO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.dto.questions.TrueFalseQuestionDTO;
import edu.ntnu.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionControllerTest {

  @Mock
  private QuestionService questionService;

  @InjectMocks
  private QuestionController questionController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateMultipleChoiceQuestion() {
    // Given
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    when(questionService.createQuestion(questionDTO)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

    // When
    ResponseEntity<QuestionDTO> responseEntity = questionController.createQuestion(questionDTO);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    verify(questionService).createQuestion(questionDTO);
  }

  @Test
  void testCreateDragDropQuestion() {
    // Given
    DragDropQuestionDTO questionDTO = new DragDropQuestionDTO();
    when(questionService.createQuestion(questionDTO)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

    // When
    ResponseEntity<QuestionDTO> responseEntity = questionController.createQuestion(questionDTO);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    verify(questionService).createQuestion(questionDTO);
  }

  @Test
  void testCreateTextInputQuestion() {
    // Given
    TextInputQuestionDTO questionDTO = new TextInputQuestionDTO();
    when(questionService.createQuestion(questionDTO)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

    // When
    ResponseEntity<QuestionDTO> responseEntity = questionController.createQuestion(questionDTO);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    verify(questionService).createQuestion(questionDTO);
  }

  @Test
  void testCreateTrueFalseQuestion() {
    // Given
    TrueFalseQuestionDTO questionDTO = new TrueFalseQuestionDTO();
    when(questionService.createQuestion(questionDTO)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

    // When
    ResponseEntity<QuestionDTO> responseEntity = questionController.createQuestion(questionDTO);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    verify(questionService).createQuestion(questionDTO);
  }

  @Test
  void testDeleteQuestion() {
    // Given
    Long questionId = 1L;
    when(questionService.deleteQuestion(questionId)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    // When
    ResponseEntity<String> responseEntity = questionController.deleteQuestion(questionId);

    // Then
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    verify(questionService).deleteQuestion(questionId);
  }

  @Test
  void testUpdateQuestion() {
    // Given
    QuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    when(questionService.updateQuestion(questionDTO)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

    // When
    ResponseEntity<String> responseEntity = questionController.updateQuestion(questionDTO);

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    verify(questionService).updateQuestion(questionDTO);
  }
}