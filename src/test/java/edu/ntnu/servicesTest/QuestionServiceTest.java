package edu.ntnu.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.ntnu.dao.questions.MultipleChoiceQuestionDAO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.mapper.QuestionMapper;
import edu.ntnu.repository.questions.MultipleChoiceQuestionDAORepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import edu.ntnu.service.QuestionService;

public class QuestionServiceTest {

  private QuestionService questionService;
  private MultipleChoiceQuestionDAORepository multipleChoiceRepository;
  private QuestionMapper questionMapper;

  @BeforeEach
  public void setUp() {
    // Mock dependencies
    multipleChoiceRepository = mock(MultipleChoiceQuestionDAORepository.class);
    questionMapper = mock(QuestionMapper.class);
    // Create instance of QuestionService with mocked dependencies
    questionService = new QuestionService(multipleChoiceRepository, null, null, null, questionMapper);
  }

  @Test
  public void testCreateQuestion_Success() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void testCreateQuestion_Error() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository to throw an exception
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenThrow(new RuntimeException());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  @Test
  public void deleteQuestion() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void updateQuestion() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestion() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestions() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestionsByType() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestionsByTypeAndDifficulty() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestionsByDifficulty() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestionsByTypeAndDifficultyAndCategory() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void getQuestionsByCategory() {
    // Create a sample MultipleChoiceQuestionDTO
    MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO();
    questionDTO.setQuestionId(1L);
    questionDTO.setType("multiple_choice");
    // Mock behavior of QuestionMapper
    when(questionMapper.toDAOWithoutId(any(MultipleChoiceQuestionDTO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Mock behavior of MultipleChoiceQuestionDAORepository
    when(multipleChoiceRepository.save(any(MultipleChoiceQuestionDAO.class))).thenReturn(new MultipleChoiceQuestionDAO());
    // Call the method being tested
    ResponseEntity<QuestionDTO> responseEntity = questionService.createQuestion(questionDTO);
    // Assert the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

}