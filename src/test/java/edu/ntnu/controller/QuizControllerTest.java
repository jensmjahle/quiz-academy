package edu.ntnu.controller;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizControllerTest {

  @Mock
  private QuizService quizService;

  @InjectMocks
  private QuizController quizController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
  }

  @Test
  void testGetQuiz() throws Exception {
    Long quizId = 1L;
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizId(quizId);

    when(quizService.getQuiz(quizId)).thenReturn(ResponseEntity.ok(quizDTO));

    mockMvc.perform(get("/quiz/" + quizId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testGetAllQuizzes() throws Exception {
    String username = "testUser";
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizName("Quiz1");
    List<QuizDTO> quizzes = Arrays.asList(quizDTO);

    when(quizService.getAllQuizzes(username)).thenReturn(ResponseEntity.ok(quizzes));

    mockMvc.perform(get("/quiz/all/" + username)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testDeleteQuiz() throws Exception {
    Long quizId = 1L;

    when(quizService.deleteQuiz(quizId)).thenReturn(ResponseEntity.ok("Quiz deleted successfully"));

    mockMvc.perform(delete("/quiz/delete/" + quizId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testCreateQuiz() throws Exception {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizName("Quiz1");

    when(quizService.createQuiz(quizDTO)).thenReturn(ResponseEntity.ok(quizDTO));

    mockMvc.perform(post("/quiz/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"quizName\":\"Quiz1\"}"))
            .andExpect(status().isOk());
  }

  @Test
  void testGetAllPublicQuizzes() throws Exception {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizName("Quiz1");
    List<QuizDTO> quizzes = Arrays.asList(quizDTO);

    when(quizService.getAllPublicQuizzes()).thenReturn(ResponseEntity.ok(quizzes));

    mockMvc.perform(get("/quiz/all")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testUpdateQuiz() throws Exception {
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizName("Quiz1");

    when(quizService.updateQuiz(quizDTO)).thenReturn(ResponseEntity.ok(quizDTO));

    mockMvc.perform(post("/quiz/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"quizName\":\"Quiz1\"}"))
            .andExpect(status().isOk());
  }

  @Test
  void testGetAllPublicQuizzesByTag() throws Exception {
    Long tagId = 1L;
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizName("Quiz1");
    List<QuizDTO> quizzes = Arrays.asList(quizDTO);

    when(quizService.getAllPublicQuizzesByTag(tagId)).thenReturn(ResponseEntity.ok(quizzes));

    mockMvc.perform(get("/quiz/public/all/tags/" + tagId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testGetAllPublicQuizzesBySearchString() throws Exception {
    String searchString = "Quiz1";
    QuizDTO quizDTO = new QuizDTO();
    quizDTO.setQuizName("Quiz1");
    List<QuizDTO> quizzes = Arrays.asList(quizDTO);

    when(quizService.getAllPublicQuizzesBySearchString(searchString)).thenReturn(ResponseEntity.ok(quizzes));

    mockMvc.perform(get("/quiz/public/all/" + searchString)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}