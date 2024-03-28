package edu.ntnu.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the application.
 */
@Configuration
public class AppConfig {


  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
