package edu.ntnu.UtilTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionDTODeserializerTest {

  @Test
  void testCreateValidMultipleChoiceJsonNode() {
    ObjectMapper mapper = new ObjectMapper(); // Initialize ObjectMapper

    try {
      JsonNode jsonNode = createValidMultipleChoiceJsonNode(mapper);
      assertNotNull(jsonNode);
    } catch (IOException e) {
      fail("Exception occurred: " + e.getMessage());
    }
  }

  private JsonNode createValidMultipleChoiceJsonNode(ObjectMapper mapper) throws IOException {
    ArrayNode options = mapper.createArrayNode().add("Option 1").add("Option 2");

    ObjectNode node = mapper.createObjectNode();
    node.put("type", "MULTIPLE_CHOICE");
    node.set("options", options);
    node.put("answer", 0);

    return node;
  }
}