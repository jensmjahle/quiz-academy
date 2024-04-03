package edu.ntnu.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.ntnu.dto.questions.DragDropQuestionDTO;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.ntnu.dto.questions.MultipleChoiceQuestionDTO;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.dto.questions.TextInputQuestionDTO;

import edu.ntnu.dto.questions.TrueFalseQuestionDTO;
import java.io.IOException;

public class QuestionDTODeserializer extends StdDeserializer<QuestionDTO> {

  public QuestionDTODeserializer() {
    this(null);
  }

  public QuestionDTODeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public QuestionDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    String type = node.get("type").asText();

    // Remove the type field before deserializing the object
    JsonNode tempNode = node.deepCopy();
    ((ObjectNode) tempNode).remove("type");

    if ("MULTIPLE_CHOICE".equals(type)) {
      return jp.getCodec().treeToValue(tempNode, MultipleChoiceQuestionDTO.class);
    } else if ("TEXT_INPUT".equals(type)) {
      return jp.getCodec().treeToValue(tempNode, TextInputQuestionDTO.class);
    } else if ("DRAG_AND_DROP".equals(type)) {
      return jp.getCodec().treeToValue(tempNode, DragDropQuestionDTO.class);
    } else if ("TRUE_FALSE".equals(type)) {
      return jp.getCodec().treeToValue(tempNode, TrueFalseQuestionDTO.class);
    }

    throw new IllegalArgumentException("Unknown question type: " + type);
  }
}
