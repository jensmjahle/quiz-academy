package edu.ntnu.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the application.
 */
@Configuration
public class AppConfig {

  /**
   * Creates a new ModelMapper instance.
   *
   * @return the ModelMapper instance
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
