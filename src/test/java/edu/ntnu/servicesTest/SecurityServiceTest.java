package edu.ntnu.servicesTest;

import edu.ntnu.dao.UserDAO;
import edu.ntnu.dto.UserDTO;
import edu.ntnu.repository.UserRepository;
import edu.ntnu.service.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.ntnu.mapper.UserMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class SecurityServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;  // Mocking PasswordEncoder

  @InjectMocks
  private SecurityService securityService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);  // Ensure mocking setup

    // Initialize the passwordEncoder object
    passwordEncoder = Mockito.mock(PasswordEncoder.class);

    // Set the passwordEncoder in the SecurityService object
    ReflectionTestUtils.setField(securityService, "passwordEncoder", passwordEncoder);
  }

  @Test
  void testCorrectPassword() {

    UserMapper userMapper = Mockito.mock(UserMapper.class);  // Optional mocking

    String username = "newUser";
    String password = "password";
    String email = "newUser@example.com";
    String firstName = "New";
    String lastName = "User";

    UserDTO userDTO = new UserDTO(username, password, email, firstName, lastName);
    UserDAO userDAO = new UserDAO(username, password, email, firstName, lastName);

    when(userMapper.toDAO(userDTO)).thenReturn(userDAO);  // Optional mocking
    when(userRepository.findByUsername(username)).thenReturn(userDAO);
    // Mock successful password match
    when(passwordEncoder.matches(password, userDAO.getPassword())).thenReturn(true);

    boolean isCorrect = securityService.correctPassword(username, password);

    assertTrue(isCorrect);
  }
}