package edu.ntnu.servicesTest;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.mapper.UserMapper;
import edu.ntnu.dao.UserDAO;
import edu.ntnu.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import edu.ntnu.service.UserService;
import org.springframework.http.ResponseEntity;
import edu.ntnu.controller.UserController;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private UserMapper userMapper;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    passwordEncoder = Mockito.mock(PasswordEncoder.class);
  }

  @Test
  void testGetUserByUsername_UserFound() {
    String username = "testUser";
    UserDAO userDAO = new UserDAO();
    userDAO.setUsername(username);
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(username);
    when(userRepository.findByUsername(username)).thenReturn(userDAO);
    when(userMapper.toDTO(userDAO)).thenReturn(userDTO);

    ResponseEntity<UserDTO> responseEntity = userService.getUserByUsername(username);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(username, responseEntity.getBody().getUsername());
  }

  @Test
  void testCreateUser_ErrorCreatingUser() {
    String username = "newUser";
    String password = "password";
    String email = "newUser@example.com";
    String firstName = "New";
    String lastName = "User";

    UserDTO userDTO = new UserDTO(username, password, email, firstName, lastName);
    UserDAO userDAO = new UserDAO(username, password, email, firstName, lastName);

    when(userMapper.toDAO(userDTO)).thenReturn(userDAO);
    when(userRepository.findByUsername(username)).thenReturn(null);
    when(passwordEncoder.encode(password)).thenThrow(new RuntimeException("Error encoding password"));

    ResponseEntity<String> responseEntity = userService.createUser(userDTO);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    assertEquals("Error creating user", responseEntity.getBody());
  }

  @Test
  void testCreateUser_UsernameExists() {
    String username = "existingUser";
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(username);
    UserDAO userDAO = new UserDAO();
    userDAO.setUsername(username);
    when(userMapper.toDAO(userDTO)).thenReturn(userDAO);
    when(userRepository.findByUsername(username)).thenReturn(userDAO);

    ResponseEntity<String> responseEntity = userService.createUser(userDTO);

    assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    assertEquals("Username already exists", responseEntity.getBody());
  }

  @Test
  void testDeleteUser_UserDeleted() {
    String username = "existingUser";
    UserDAO userDAO = new UserDAO();
    userDAO.setUsername(username);
    when(userRepository.findByUsername(username)).thenReturn(userDAO);

    ResponseEntity<String> responseEntity = userService.deleteUser(username);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("User deleted successfully", responseEntity.getBody());
  }

  @Test
  void testDeleteUser_UserNotFound() {
    String username = "nonExistentUser";
    when(userRepository.findByUsername(username)).thenReturn(null);

    ResponseEntity<String> responseEntity = userService.deleteUser(username);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("User not found", responseEntity.getBody());
  }
}