package edu.ntnu;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class QuizAcademyApplication {

	/**
	 * The main method of the application.
	 */
	@GetMapping("/")
	public String home() {
		return "Spring is here!";
	}

	public static void main(String[] args) {
		SpringApplication.run(QuizAcademyApplication.class, args);




	}
}
