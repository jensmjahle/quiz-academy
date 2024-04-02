package edu.ntnu.mapper;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.model.Quiz;
import edu.ntnu.model.questions.Question;
import edu.ntnu.service.QuestionService;
import edu.ntnu.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {
  private final Logger logger = Logger.getLogger(QuizMapper.class.getName());
  private final UserService userService;
  private final QuestionService questionService;
  private final TagMapper tagMapper;


  @Autowired
  public QuizMapper(UserService userService, TagMapper tagMapper, QuestionService questionService) {
    this.userService = userService;
    this.questionService = questionService;
    this.tagMapper = tagMapper;
  }

    public QuizDTO toQuizDTOWithoutQuestions(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(quiz.getQuizId());
        quizDTO.setQuizName(quiz.getQuizName());
        quizDTO.setQuizDescription(quiz.getQuizDescription());
        quizDTO.setUser(quiz.getUser().getUsername());
        quizDTO.setTags(quiz.getTags().stream().map(tagMapper::toTagDTO).collect(Collectors.toList()));
        quizDTO.setQuizCreationDate(quiz.getQuizCreationDate());
        return quizDTO;
    }

    public QuizDTO toQuizDTO(Quiz quiz) {
        QuizDTO quizDTO = toQuizDTOWithoutQuestions(quiz);

        // Get all questions for the quiz from the database
        Iterable<Question> questions = questionService.getAllQuestions(quiz.getQuizId());

        // Map the questions to QuestionDTO objects
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Question question : questions) {
          questionDTOs.add(QuestionMapper.toQuestionDTO(question));
        }
        quizDTO.setQuestions(questionDTOs);

        return quizDTO;
    }

    public Quiz toQuizWithoutId(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizDTO.getQuizName());
        quiz.setQuizDescription(quizDTO.getQuizDescription());
        quiz.setQuizCreationDate(quizDTO.getQuizCreationDate());

        // Map tagDTOs to Tag objects
        if (quizDTO.getTags() != null) {
            quiz.setTags(quizDTO.getTags().stream().map(tagMapper::toTag).collect(Collectors.toList()));
        } else {
            quiz.setTags(null);
            logger.warning("No tags found for quiz with id " + quizDTO.getQuizId() + ". Setting tags to null.");
        }

        // Retrieve the user from the database and map it to the quiz
        quiz.setUser(userService.getUserObjectByUsername(quizDTO.getUser()));

        return quiz;
    }

    public Quiz toQuiz(QuizDTO quizDTO) {
        Quiz quiz = toQuizWithoutId(quizDTO);
        quiz.setQuizId(quizDTO.getQuizId());
        return quiz;
    }

}
