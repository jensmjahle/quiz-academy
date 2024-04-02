package edu.ntnu.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.ntnu.dto.questions.QuestionDTO;
import edu.ntnu.utils.QuestionDTODeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(QuestionDTO.class, new QuestionDTODeserializer());
    objectMapper.registerModule(module);
    return objectMapper;
  }
}
