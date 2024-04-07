package edu.ntnu.mapper;

import edu.ntnu.dto.QuizDTO;
import edu.ntnu.dto.TagDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dao.QuizDAO;
import edu.ntnu.dao.TagDAO;
import edu.ntnu.dao.questions.QuestionDAO;
import edu.ntnu.repository.TagRepository;
import edu.ntnu.service.QuestionService;
import edu.ntnu.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper class for the Quiz entity.
 */
@Component
public class QuizMapper {
  private final Logger logger = Logger.getLogger(QuizMapper.class.getName());
  private final UserService userService;
  private final QuestionService questionService;
  private final QuestionMapper questionMapper;
  private final TagRepository tagRepository;
  private final TagMapper tagMapper;


  /**
   * Constructor for the QuizMapper class.
   * @param userService The UserService object.
   * @param tagMapper The TagMapper object.
   * @param questionService The QuestionService object.
   * @param tagRepository The TagRepository object.
   * @param questionMapper The QuestionMapper object.
   */
  @Autowired
  public QuizMapper(
      UserService userService,
      TagMapper tagMapper,
      QuestionService questionService,
      TagRepository tagRepository,
      QuestionMapper questionMapper) {
    this.userService = userService;
    this.questionService = questionService;
    this.tagMapper = tagMapper;
    this.tagRepository = tagRepository;
    this.questionMapper = questionMapper;
  }

    /**
     * Maps a QuizDAO object to a QuizDTO object without the questions.
     * @param quizDAO The QuizDAO object to map.
     * @return The QuizDTO object.
     */
    public QuizDTO toQuizDTOWithoutQuestions(QuizDAO quizDAO) {
    try {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(quizDAO.getQuizId());
        quizDTO.setQuizName(quizDAO.getQuizName());
        quizDTO.setQuizDescription(quizDAO.getQuizDescription());
        quizDTO.setUser(quizDAO.getUser().getUsername());
        quizDTO.setIsPublic(quizDAO.isPublic());
        quizDTO.setTags(quizDAO.getTags().stream().map(tagMapper::toTagDTO).collect(Collectors.toList()));
        quizDTO.setQuizCreationDate(quizDAO.getQuizCreationDate());
        return quizDTO;
    } catch (Exception e) {
        logger.warning("Failed to map quiz with id " + quizDAO.getQuizId() + " to QuizDTO.");
        throw e;
    }
    }

    /**
     * Maps a QuizDAO object to a QuizDTO object.
     * @param quizDAO The QuizDAO object to map.
     * @return The QuizDTO object.
     */
    public QuizDTO toQuizDTO(QuizDAO quizDAO) {
        QuizDTO quizDTO = toQuizDTOWithoutQuestions(quizDAO);

        // Get all questions for the quiz from the database
        Iterable<QuestionDAO> questions = questionService.getAllQuestions(quizDAO.getQuizId());

        // Map the questions to QuestionDTO objects
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (QuestionDAO questionDAO : questions) {
          questionDTOs.add(questionMapper.toDTO(questionDAO));
        }
        quizDTO.setQuestions(questionDTOs);

        return quizDTO;
    }

    /**
     * Maps a QuizDAO object to a QuizDTO object.
     * @param quizDAO The QuizDAO object to map.
     * @return The QuizDTO object.
     */
    public QuizDAO toDAOWithoutId(QuizDTO quizDTO) {
        QuizDAO quizDAO = new QuizDAO();
        quizDAO.setQuizName(quizDTO.getQuizName());
        quizDAO.setQuizDescription(quizDTO.getQuizDescription());
        quizDAO.setQuizCreationDate(quizDTO.getQuizCreationDate());
        quizDAO.setIsPublic(quizDTO.isPublic());

        // Map tagDTOs to Tag objects
      List <TagDTO> tags = quizDTO.getTags();
        if (tags != null && !tags.isEmpty()) {
          for (TagDTO tagDTO : tags) {
            TagDAO tagDAO = tagMapper.toDAO(tagDTO);
            quizDAO.addTag(tagDAO);
          }
        } else {
            quizDAO.setTags(null);
            logger.warning("No tags found for quiz with id " + quizDTO.getQuizId() + ". Setting tags to null.");
        }

        // Retrieve the user from the database and map it to the quiz
        quizDAO.setUser(userService.getUserObjectByUsername(quizDTO.getUser()));

        return quizDAO;
    }

    /**
     * Maps a QuizDTO object to a QuizDAO object.
     * @param quizDTO The QuizDTO object to map.
     * @return The QuizDAO object.
     */
    public QuizDAO toDAO(QuizDTO quizDTO) {
        QuizDAO quizDAO = toDAOWithoutId(quizDTO);
        quizDAO.setQuizId(quizDTO.getQuizId());
        return quizDAO;
    }

}
