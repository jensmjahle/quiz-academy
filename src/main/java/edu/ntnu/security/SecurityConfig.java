package edu.ntnu.security;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EntityScan(basePackages = {"edu.ntnu.dao", "edu.ntnu.dao.questions"})
public class SecurityConfig {

  // inject SecurityFilterChain and tell that all requests are authenticated
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().and()
        .authorizeHttpRequests()
        .requestMatchers("/token").permitAll()
        .anyRequest().authenticated().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
