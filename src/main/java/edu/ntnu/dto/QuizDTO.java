package edu.ntnu.dto;

import edu.ntnu.dto.questions.QuestionDTO;
import java.util.Date;
import java.util.List;
import org.springframework.lang.Nullable;

public class QuizDTO {
  private Long quizId;
  private String quizName;
  private String quizDescription;
  private String user;
  private boolean isPublic;
  @Nullable
  private List<TagDTO> tags;
  private Date quizCreationDate;
  @Nullable
  private List<QuestionDTO> questions;

  public QuizDTO() {
  }

  public QuizDTO(Long quizId, String quizName, String quizDescription, String user, List<TagDTO> tags, Date quizCreationDate, List<QuestionDTO> questions, boolean isPublic) {
    this.quizId = quizId;
    this.quizName = quizName;
    this.quizDescription = quizDescription;
    this.user = user;
    this.tags = tags;
    this.quizCreationDate = quizCreationDate;
    this.questions = questions;
    this.isPublic = isPublic;

  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  public String getQuizName() {
    return quizName;
  }

  public void setQuizName(String quizName) {
    this.quizName = quizName;
  }

  public String getQuizDescription() {
    return quizDescription;
  }

  public void setQuizDescription(String quizDescription) {
    this.quizDescription = quizDescription;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String username) {
    this.user = username;
  }

  public List<TagDTO> getTags() {
    return tags;
  }

  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }

  public Date getQuizCreationDate() {
    return quizCreationDate;
  }

  public void setQuizCreationDate(Date quizCreationDate) {
    this.quizCreationDate = quizCreationDate;
  }


  public void setQuestions(List<QuestionDTO> questions) {
    this.questions = questions;
  }

  public void addQuestion(QuestionDTO question) {
    this.questions.add(question);
  }

  public void removeQuestion(QuestionDTO question) {
    this.questions.remove(question);
  }

  public void addTag(TagDTO tag) {
    this.tags.add(tag);
  }

  public void removeTag(TagDTO tag) {
    this.tags.remove(tag);
  }
  public boolean isPublic() {
    return isPublic;
  }
  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  @Override
  public String toString() {
    return "QuizDTO{" +
        "quizId=" + quizId +
        ", quizName='" + quizName + '\'' +
        ", quizDescription='" + quizDescription + '\'' +
        ", user=" + user +
        ", tags=" + tags +
        ", quizCreationDate=" + quizCreationDate +
        ", questions=" + questions +
        '}';
  }

  public List<QuestionDTO> getQuestions() {
    return questions;
  }
}
