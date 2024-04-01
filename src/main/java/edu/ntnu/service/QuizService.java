package edu.ntnu.service;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.TagDTO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.model.Quiz;
import edu.ntnu.model.Tag;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.TextInputQuestion;
import edu.ntnu.repository.questions.MultipleChoiceQuestionRepository;
import edu.ntnu.repository.questions.TextInputQuestionRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import edu.ntnu.repository.QuizRepository;

@Service
public class QuizService {

  private final QuizRepository quizRepository;
  private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
  private final TextInputQuestionRepository textInputQuestionRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  private final Logger logger = Logger.getLogger(QuizService.class.getName());

  @Autowired
  public QuizService(QuizRepository quizRepository, MultipleChoiceQuestionRepository multipleChoiceQuestionRepository, TextInputQuestionRepository textInputQuestionRepository) {
    this.quizRepository = quizRepository;
    this.multipleChoiceQuestionRepository = multipleChoiceQuestionRepository;
    this.textInputQuestionRepository = textInputQuestionRepository;
  }

  public ResponseEntity<QuizDTO> getQuiz(Long quizId) {
   try {
    logger.info("Received request for quiz with id: " + quizId);
    Quiz quiz = quizRepository.findByQuizId(quizId);
    QuizDTO quizDTO = modelMapper.map(quiz, QuizDTO.class);


    if (quizDTO != null) {
      logger.info("Quiz with id " + quizId + " found. Returning quiz.");
      return ResponseEntity.ok(quizDTO);
    } else {
      logger.info("Quiz with id " + quizId + " not found.");
      return ResponseEntity.notFound().build();
    }
  } catch (Exception e) {
    logger.severe("An error occurred while getting quiz with id " + quizId + ": " + e.getMessage());
    return ResponseEntity.status(500).build();
  }
  }

  public ResponseEntity<Iterable<QuizDTO>> getAllQuizzes(String username) {
    try {
      logger.info("Received request for all quizzes from user: " + username);

      Iterable<Quiz> quizzes = quizRepository.findAllByUser_Username(username);
      if (quizzes != null) {

        // Convert quizzes to DTOs
        Iterable<QuizDTO> quizDTOs = mapQuizzesToDTO(quizzes);

        int numQuizzes = ((List<QuizDTO>) quizDTOs).size();
        logger.info(numQuizzes + " quizzes found. Returning quizzes.");
        return ResponseEntity.ok(quizDTOs);
      } else {
        logger.info("No quizzes found.");
        return ResponseEntity.notFound().build();
      }
    } catch (Exception e) {
      logger.severe("An error occurred while getting all quizzes: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  /**
   * Maps a Quiz object to a QuizDTO object.
   * Includes tags and questions that are associated with the quiz.
   * Questions and tags are retrieved from the database and added to the DTO.
   *
   * @param quizzes The quizzes to map to DTOs.
   * @return A list of QuizDTOs.
   */
  private List<QuizDTO> mapQuizzesToDTO(Iterable<Quiz> quizzes) {
    try {
      List<QuizDTO> quizDTOs = new ArrayList<>();

      for (Quiz quiz : quizzes) {
        QuizDTO quizDTO = new QuizDTO(
            quiz.getQuizId(),
            quiz.getQuizName(),
            quiz.getQuizDescription(),
            quiz.getUser().getUsername(),
            new ArrayList<>(),
            quiz.getQuizCreationDate(),
            new ArrayList<>()
        );


        // Add tags to the DTO //
        Iterable<Tag> quizTags = quizRepository.findAllTagsByQuizId(quiz.getQuizId());
        System.out.println("hello");
        if (quizTags != null) {
          List<TagDTO> tags = new ArrayList<>();
          for (Tag tag : quizTags) {
            TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
            tags.add(tagDTO);
          }
          quizDTO.setTags(tags);
        }


        // Add questions to the DTO //
        List<MultipleChoiceQuestion> multipleChoiceQuestions = (List<MultipleChoiceQuestion>) multipleChoiceQuestionRepository.findAllByQuiz_QuizId(quiz.getQuizId());
        List<TextInputQuestion> textInputQuestions = (List<TextInputQuestion>) textInputQuestionRepository.findAllByQuiz_QuizId(quiz.getQuizId());

        // MultipleChoiceQuestion
        if (multipleChoiceQuestions != null) {
          for (MultipleChoiceQuestion multipleChoiceQuestion : multipleChoiceQuestions) {
            MultipleChoiceQuestionDTO multipleChoiceQuestionDTO = QuestionService.convertToMultipleChoiceQuestionDTO(multipleChoiceQuestion);
            quizDTO.addQuestion(multipleChoiceQuestionDTO);
          }
        }

        // TextInputQuestion
        if (textInputQuestions != null) {
          for (TextInputQuestion textInputQuestion : textInputQuestions) {
            TextInputQuestionDTO textInputQuestionDTO = QuestionService.convertToTextInputQuestionDTO(textInputQuestion);
            quizDTO.addQuestion(textInputQuestionDTO);
          }
        }

        quizDTOs.add(quizDTO);
      }
      return quizDTOs;
    } catch (Exception e) {
      logger.severe("An error occurred while mapping quizzes to DTOs: " + e.getMessage());
      throw e;
    }
    }




}
