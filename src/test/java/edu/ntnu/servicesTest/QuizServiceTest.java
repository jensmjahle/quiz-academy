package edu.ntnu.servicesTest;

import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dto.QuizDTO;
import edu.ntnu.mapper.QuizMapper;
import edu.ntnu.repository.QuizRepository;
import edu.ntnu.repository.questions.DragDropQuestionDAORepository;
import edu.ntnu.repository.questions.MultipleChoiceQuestionDAORepository;
import edu.ntnu.repository.questions.TextInputQuestionDAORepository;
import edu.ntnu.repository.questions.TrueFalseQuestionDAORepository;
import edu.ntnu.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import edu.ntnu.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QuizServiceTest {

  @Mock
  private QuizRepository quizRepository;

  @Mock
  private MultipleChoiceQuestionDAORepository multipleChoiceRepository;

  @Mock
  private TextInputQuestionDAORepository textInputRepository;

  @Mock
  private DragDropQuestionDAORepository dragDropRepository;

  @Mock
  private TrueFalseQuestionDAORepository trueFalseRepository;

  @Mock
  private QuestionService questionService;

  @Mock
  private QuizMapper quizMapper;

  @InjectMocks
  private QuizService quizService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    quizService = new QuizService(
            quizRepository,
            multipleChoiceRepository,
            textInputRepository,
            dragDropRepository,
            trueFalseRepository,
            quizMapper,
            questionService
    );
  }

  @Test
  public void testGetQuiz() {
    // Mocking QuizDAO and QuizDTO
    QuizDAO mockQuizDAO = new QuizDAO();
    mockQuizDAO.setQuizId(1L);
    QuizDTO mockQuizDTO = new QuizDTO();
    mockQuizDTO.setQuizId(1L);

    when(quizRepository.findByQuizId(1L)).thenReturn(mockQuizDAO);
    when(quizMapper.toQuizDTO(mockQuizDAO)).thenReturn(mockQuizDTO);

    ResponseEntity<QuizDTO> responseEntity = quizService.getQuiz(1L);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTO, responseEntity.getBody());
  }

  @Test
  public void testGetAllQuizzes() {
    // Mocking QuizDAO list and QuizDTO list
    List<QuizDAO> mockQuizDAOList = new ArrayList<>();
    QuizDAO mockQuizDAO1 = new QuizDAO();
    mockQuizDAO1.setQuizId(1L);
    QuizDAO mockQuizDAO2 = new QuizDAO();
    mockQuizDAO2.setQuizId(2L);
    mockQuizDAOList.add(mockQuizDAO1);
    mockQuizDAOList.add(mockQuizDAO2);

    List<QuizDTO> mockQuizDTOList = new ArrayList<>();
    QuizDTO mockQuizDTO1 = new QuizDTO();
    mockQuizDTO1.setQuizId(1L);
    QuizDTO mockQuizDTO2 = new QuizDTO();
    mockQuizDTO2.setQuizId(2L);
    mockQuizDTOList.add(mockQuizDTO1);
    mockQuizDTOList.add(mockQuizDTO2);

    when(quizRepository.findAllByUserDAO_Username(anyString())).thenReturn(mockQuizDAOList);
    when(quizMapper.toQuizDTO(any(QuizDAO.class))).thenReturn(mockQuizDTO1, mockQuizDTO2);

    ResponseEntity<Iterable<QuizDTO>> responseEntity = quizService.getAllQuizzes("username");

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTOList, responseEntity.getBody());
  }

  @Test
  public void testCreateQuiz() {
    // Mocking QuizDTO
    QuizDTO mockQuizDTO = new QuizDTO();
    mockQuizDTO.setQuizId(1L);

    // Mocking save operation
    when(quizMapper.toDAOWithoutId(mockQuizDTO)).thenReturn(new QuizDAO());
    when(quizRepository.save(any(QuizDAO.class))).thenReturn(new QuizDAO());
    when(quizMapper.toQuizDTO(any(QuizDAO.class))).thenReturn(mockQuizDTO);

    ResponseEntity<QuizDTO> responseEntity = quizService.createQuiz(mockQuizDTO);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTO, responseEntity.getBody());
  }

  @Test
  public void testDeleteQuiz() {
    ResponseEntity<String> responseEntity = quizService.deleteQuiz(1L);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("Quiz deleted successfully.", responseEntity.getBody());
  }

  @Test
  public void testUpdateQuiz() {
    // Mocking QuizDTO
    QuizDTO mockQuizDTO = new QuizDTO();
    mockQuizDTO.setQuizId(1L);
    mockQuizDTO.setQuestions(new ArrayList<>()); // Initialize the questions list

    // Mocking QuizDAO
    QuizDAO mockQuizDAO = new QuizDAO();
    mockQuizDAO.setQuizId(1L);

    // Mocking delete and create operations
    when(quizMapper.toDAOWithoutId(mockQuizDTO)).thenReturn(mockQuizDAO); // Return QuizDAO when mapping
    when(quizRepository.save(any(QuizDAO.class))).thenReturn(mockQuizDAO); // Return QuizDAO when saving
    when(quizMapper.toQuizDTO(mockQuizDAO)).thenReturn(mockQuizDTO); // Return QuizDTO when mapping

    ResponseEntity<QuizDTO> responseEntity = quizService.updateQuiz(mockQuizDTO);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTO, responseEntity.getBody());
  }

  @Test
  public void testGetAllPublicQuizzes() {
    // Mocking QuizDAO list and QuizDTO list
    List<QuizDAO> mockQuizDAOList = new ArrayList<>();
    QuizDAO mockQuizDAO1 = new QuizDAO();
    mockQuizDAO1.setQuizId(1L);
    QuizDAO mockQuizDAO2 = new QuizDAO();
    mockQuizDAO2.setQuizId(2L);
    mockQuizDAOList.add(mockQuizDAO1);
    mockQuizDAOList.add(mockQuizDAO2);

    List<QuizDTO> mockQuizDTOList = new ArrayList<>();
    QuizDTO mockQuizDTO1 = new QuizDTO();
    mockQuizDTO1.setQuizId(1L);
    QuizDTO mockQuizDTO2 = new QuizDTO();
    mockQuizDTO2.setQuizId(2L);
    mockQuizDTOList.add(mockQuizDTO1);
    mockQuizDTOList.add(mockQuizDTO2);

    when(quizRepository.findAllByIsPublic(true)).thenReturn(mockQuizDAOList);
    when(quizMapper.toQuizDTO(any(QuizDAO.class))).thenReturn(mockQuizDTO1, mockQuizDTO2);

    ResponseEntity<Iterable<QuizDTO>> responseEntity = quizService.getAllPublicQuizzes();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTOList, responseEntity.getBody());
  }

  @Test
  public void testGetAllPublicQuizzesByTag() {
    // Mocking QuizDAO list and QuizDTO list
    List<QuizDAO> mockQuizDAOList = new ArrayList<>();
    QuizDAO mockQuizDAO1 = new QuizDAO();
    mockQuizDAO1.setQuizId(1L);
    QuizDAO mockQuizDAO2 = new QuizDAO();
    mockQuizDAO2.setQuizId(2L);
    mockQuizDAOList.add(mockQuizDAO1);
    mockQuizDAOList.add(mockQuizDAO2);

    List<QuizDTO> mockQuizDTOList = new ArrayList<>();
    QuizDTO mockQuizDTO1 = new QuizDTO();
    mockQuizDTO1.setQuizId(1L);
    QuizDTO mockQuizDTO2 = new QuizDTO();
    mockQuizDTO2.setQuizId(2L);
    mockQuizDTOList.add(mockQuizDTO1);
    mockQuizDTOList.add(mockQuizDTO2);

    when(quizRepository.findAllByIsPublicAndTagDAOs_TagId(true, 1L)).thenReturn(mockQuizDAOList);
    when(quizMapper.toQuizDTO(any(QuizDAO.class))).thenReturn(mockQuizDTO1, mockQuizDTO2);

    ResponseEntity<Iterable<QuizDTO>> responseEntity = quizService.getAllPublicQuizzesByTag(1L);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTOList, responseEntity.getBody());
  }

  @Test
  public void testGetAllPublicQuizzesBySearchString() {
    // Mocking QuizDAO list and QuizDTO list
    List<QuizDAO> mockQuizDAOList = new ArrayList<>();
    QuizDAO mockQuizDAO1 = new QuizDAO();
    mockQuizDAO1.setQuizId(1L);
    QuizDAO mockQuizDAO2 = new QuizDAO();
    mockQuizDAO2.setQuizId(2L);
    mockQuizDAOList.add(mockQuizDAO1);
    mockQuizDAOList.add(mockQuizDAO2);

    List<QuizDTO> mockQuizDTOList = new ArrayList<>();
    QuizDTO mockQuizDTO1 = new QuizDTO();
    mockQuizDTO1.setQuizId(1L);
    QuizDTO mockQuizDTO2 = new QuizDTO();
    mockQuizDTO2.setQuizId(2L);
    mockQuizDTOList.add(mockQuizDTO1);
    mockQuizDTOList.add(mockQuizDTO2);

    when(quizRepository.findAllPublicQuizzesWithSearchString("search")).thenReturn(mockQuizDAOList);
    when(quizMapper.toQuizDTO(any(QuizDAO.class))).thenReturn(mockQuizDTO1, mockQuizDTO2);

    ResponseEntity<Iterable<QuizDTO>> responseEntity = quizService.getAllPublicQuizzesBySearchString("search");

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockQuizDTOList, responseEntity.getBody());
  }
}