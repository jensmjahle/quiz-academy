package edu.ntnu.mapper;

import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import edu.ntnu.model.questions.MultipleChoiceQuestion;
import edu.ntnu.model.questions.Question;
import edu.ntnu.model.questions.TextInputQuestion;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
  private final Logger logger = Logger.getLogger(QuestionMapper.class.getName());
    public QuestionDTO toQuestionDTO(Question question) {
        if (question instanceof TextInputQuestion) {
            return new TextInputQuestionDTO(
                question.getQuestionId(),
                question.getQuestionText(),
                question.getQuizId(),
                (splitStringToList(((TextInputQuestion) question).getAnswer()))
            );

        } else if (question instanceof MultipleChoiceQuestion) {
            return new MultipleChoiceQuestionDTO(
                question.getQuestionId(),
                question.getQuestionText(),
                question.getQuizId(),
                (splitStringToList(((MultipleChoiceQuestion) question).getAlternatives())),
                (splitStringToList(((MultipleChoiceQuestion) question).getCorrectAlternatives()))
            );
        } else {
            throw new IllegalArgumentException("Unknown question type. Cannot convert to DTO");
        }
    }

    public Question toQuestion(QuestionDTO questionDTO) {
        if (questionDTO instanceof TextInputQuestionDTO) {
            return new TextInputQuestion(
                questionDTO.getQuestionId(),
                questionDTO.getQuestionText(),
                questionDTO.getQuizId(),
                (joinListToString(((TextInputQuestionDTO) questionDTO).getAnswers()))
            );
        } else if (questionDTO instanceof MultipleChoiceQuestionDTO) {
            return new MultipleChoiceQuestion(
                questionDTO.getQuestionId(),
                questionDTO.getQuestionText(),
                questionDTO.getQuizId(),
                (joinListToString(((MultipleChoiceQuestionDTO) questionDTO).getAlternatives())),
                (joinListToString(((MultipleChoiceQuestionDTO) questionDTO).getCorrectAlternatives()))
            );
        } else {
            throw new IllegalArgumentException("Unknown question type. Cannot convert to model");
        }
    }

    public Question toQuestionWithoutId(QuestionDTO questionDTO) {
        if (questionDTO instanceof TextInputQuestionDTO) {
          TextInputQuestion question = new TextInputQuestion();
          question.setQuestionText(questionDTO.getQuestionText());
          question.setQuizId(questionDTO.getQuizId());
          question.setAnswer(joinListToString(((TextInputQuestionDTO) questionDTO).getAnswers()));
          logger.info("Converted to text input question without id: " + question);
          return question;
        } else if (questionDTO instanceof MultipleChoiceQuestionDTO) {
          MultipleChoiceQuestion question = new MultipleChoiceQuestion();
          question.setQuestionText(questionDTO.getQuestionText());
          question.setQuizId(questionDTO.getQuizId());
          question.setAlternatives(joinListToString(((MultipleChoiceQuestionDTO) questionDTO).getAlternatives()));
          question.setCorrectAlternatives(joinListToString(((MultipleChoiceQuestionDTO) questionDTO).getCorrectAlternatives()));
          logger.info("Converted to multiple choice question without id: " + question);
          return question;
        } else {
            throw new IllegalArgumentException("Unknown question type. Cannot convert to model");
        }
    }

  private List<String> splitStringToList(String input) {
    String[] parts = input.split("\\*");

    return Arrays.asList(parts);
  }
  private String joinListToString(List<String> list) {
    return String.join("*", list);
  }
}
