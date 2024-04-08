package edu.ntnu;

import edu.ntnu.dao.UserDAO;
import edu.ntnu.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class QuizAcademyApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	private UserDAO testUser;

	@BeforeEach
	void setUp() {
		// Create a new user
		testUser = new UserDAO();
		testUser.setUsername("validUsername");
		testUser.setPassword("validPassword");
		userRepository.save(testUser);  // Save the user to the database
	}

	@AfterEach
	void tearDown() {
		// Delete the test user from the database after each test
		userRepository.delete(testUser);
	}

	@Test
	void homeResponse() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(testUser.getUsername(), testUser.getPassword());
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<String> response = this.restTemplate.exchange("/", HttpMethod.GET, entity, String.class);
		assertEquals("Spring is here!", response.getBody());
	}
}