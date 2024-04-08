package edu.ntnu.mapperTests;

import edu.ntnu.dao.UserDAO;
import edu.ntnu.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.ntnu.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

  private UserMapper userMapper;

  @BeforeEach
  void setUp() {
    userMapper = new UserMapper();
  }

  @Test
  void testToDTO() {
    // Given
    UserDAO userDAO = new UserDAO("username", "password", "email@example.com", "John", "Doe");

    // When
    UserDTO userDTO = userMapper.toDTO(userDAO);

    // Then
    assertNotNull(userDTO);
    assertEquals("username", userDTO.getUsername());
    assertEquals("password", userDTO.getPassword());
    assertEquals("email@example.com", userDTO.getEmail());
    assertEquals("John", userDTO.getFirstName());
    assertEquals("Doe", userDTO.getLastName());
  }

  @Test
  void testToDAO() {
    // Given
    UserDTO userDTO = new UserDTO("username", "password", "email@example.com", "John", "Doe");

    // When
    UserDAO userDAO = userMapper.toDAO(userDTO);

    // Then
    assertNotNull(userDAO);
    assertEquals("username", userDAO.getUsername());
    assertEquals("password", userDAO.getPassword());
    assertEquals("email@example.com", userDAO.getEmail());
    assertEquals("John", userDAO.getFirstName());
    assertEquals("Doe", userDAO.getLastName());
  }
}