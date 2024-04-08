package edu.ntnu.mapperTests;

import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.TagDAO;
import edu.ntnu.dao.UserDAO;
import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.TagDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.enums.QuestionType;
import edu.ntnu.mapper.QuestionMapper;
import edu.ntnu.mapper.TagMapper;
import edu.ntnu.service.QuestionService;
import edu.ntnu.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import edu.ntnu.mapper.QuizMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class QuizMapperTest {

  private QuizMapper quizMapper;

  @Mock
  private UserService userService;

  @Mock
  private QuestionService questionService;

  @Mock
  private QuestionMapper questionMapper;

  @Mock
  private TagMapper tagMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    quizMapper = new QuizMapper(userService, tagMapper, questionService, null, questionMapper);
  }

  @Test
  void testToQuizDTOWithoutQuestions() {
    // Given
    QuizDAO quizDAO = new QuizDAO();
    quizDAO.setQuizId(1L);
    quizDAO.setQuizName("Sample Quiz");
    quizDAO.setQuizDescription("Description");
    quizDAO.setIsPublic(true);
    quizDAO.setQuizCreationDate(new Date());
    List<TagDAO> tagDAOList = new ArrayList<>();
    TagDAO tagDAO = new TagDAO();
    tagDAO.setTagName("Sample Tag");
    tagDAOList.add(tagDAO);
    quizDAO.setTags(tagDAOList);
    UserDAO userDAO = new UserDAO(); // Create a new UserDAO object
    userDAO.setUsername("username"); // Set the username
    quizDAO.setUser(userDAO); // Set the userDAO in the quizDAO
    when(tagMapper.toTagDTO(tagDAO)).thenReturn(new TagDTO(null, "Sample Tag"));
    when(userService.getUserObjectByUsername("username")).thenReturn(null);

    // When
    QuizDTO quizDTO = quizMapper.toQuizDTOWithoutQuestions(quizDAO);

    // Then
    assertEquals(1L, quizDTO.getQuizId());
    assertEquals("Sample Quiz", quizDTO.getQuizName());
    assertEquals("Description", quizDTO.getQuizDescription());
    assertEquals("username", quizDTO.getUser());
    assertEquals(true, quizDTO.isPublic());
    assertEquals(1, quizDTO.getTags().size());
    assertEquals("Sample Tag", quizDTO.getTags().get(0).getTagName());
  }

  @Test
  void testToQuizDTO() {
    // Given
    QuizDAO quizDAO = new QuizDAO();
    quizDAO.setQuizId(1L);
    quizDAO.setQuizName("Sample Quiz");
    quizDAO.setQuizDescription("Description");
    quizDAO.setIsPublic(true);
    quizDAO.setQuizCreationDate(new Date());
    List<TagDAO> tagDAOList = new ArrayList<>();
    TagDAO tagDAO = new TagDAO();
    tagDAO.setTagName("Sample Tag");
    tagDAOList.add(tagDAO);
    quizDAO.setTags(tagDAOList);
    UserDAO userDAO = new UserDAO(); // Create a new UserDAO object
    userDAO.setUsername("username"); // Set the username
    quizDAO.setUser(userDAO); // Set the userDAO in the quizDAO
    when(tagMapper.toTagDTO(tagDAO)).thenReturn(new TagDTO(null, "Sample Tag"));
    when(userService.getUserObjectByUsername("username")).thenReturn(null);
    when(questionService.getAllQuestions(1L)).thenReturn(new ArrayList<>());
    QuestionDTO mockQuestionDTO = Mockito.mock(QuestionDTO.class);
    when(questionMapper.toDTO(null)).thenReturn(mockQuestionDTO);

    // When
    QuizDTO quizDTO = quizMapper.toQuizDTO(quizDAO);

    // Then
    assertEquals(1L, quizDTO.getQuizId());
    assertEquals("Sample Quiz", quizDTO.getQuizName());
    assertEquals("Description", quizDTO.getQuizDescription());
    assertEquals("username", quizDTO.getUser());
    assertEquals(true, quizDTO.isPublic());
    assertEquals(1, quizDTO.getTags().size());
    assertEquals("Sample Tag", quizDTO.getTags().get(0).getTagName());
  }
}