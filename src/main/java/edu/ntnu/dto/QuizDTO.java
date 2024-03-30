package edu.ntnu.dto;

import edu.ntnu.dto.questions.QuestionDTO;
import java.util.Date;
import java.util.List;

public class QuizDTO {
  private Long quizId;
  private String quizName;
  private String quizDescription;
  private UserDTO user;
  private List<TagDTO> tags;
  private Date quizCreationDate;
  private List<QuestionDTO> questions;

}
