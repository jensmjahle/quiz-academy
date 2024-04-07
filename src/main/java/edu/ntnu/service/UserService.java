package edu.ntnu.service;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.mapper.UserMapper;
import edu.ntnu.dao.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import edu.ntnu.repository.UserRepository;
import java.util.logging.Logger;

/**
 * Service class for User objects.
 */
@Service
public class UserService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;
  private final ModelMapper modelMapper = new ModelMapper();
  private final UserMapper userMapper;
  private final Logger logger = Logger.getLogger(UserService.class.getName());

  @Autowired
  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  /**
   * Method to get a user by username from the database.
   * If the user is found, the status code is 200.
   * If the user is not found, the status code is 404.
   * If an error occurs, the status code is 500.
   *
   * @param username The username of the user to get.
   * @return ResponseEntity with the User object and status code.
   */
  public ResponseEntity<UserDTO> getUserByUsername(String username) {
    UserDAO userDAO = userRepository.findByUsername(username);
    UserDTO userDTO = modelMapper.map(userDAO, UserDTO.class);
    if (userDTO != null) {
      logger.info("User with username " + username + " found. Returning user.");
      return new ResponseEntity<>(userDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Method to create a user from a UserDTO object.
   * Saves the user to the database if the username is not already taken.
   * If the user is created successfully, the status code is 201.
   * If the username is already taken, the status code is 409.
   * If an error occurs, the status code is 500.
   *
   * @param  userDTO The UserDTO object to create the user from.
   * @return ResponseEntity with a message and status code.
   */
  public ResponseEntity<String> createUser(UserDTO userDTO) {
    logger.info("Received request for user creation with username: " + userDTO.getUsername());
    try {
      UserDAO userDAO = userMapper.toDAO(userDTO);
      logger.info("User object created from userDTO");

      if (userRepository.findByUsername(userDAO.getUsername()) != null) {
        logger.info("No user created, username already exists");
        return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
      } else {
        try {
          userDAO.setPassword(passwordEncoder.encode(userDAO.getPassword()));
          userRepository.save(userDAO);
          logger.info("User " + userDAO.getUsername() + " created successfully");
          return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
          logger.info("Error creating user" + e.getMessage());
          return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
    } catch (Exception e) {
      logger.info("Error creating user" + e.getMessage());
      return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public UserDAO getUserObjectByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public ResponseEntity<String> deleteUser(String username) {
    try {
      UserDAO userDAO = userRepository.findByUsername(username);
      if (userDAO != null) {
        userRepository.delete(userDAO);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("Error deleting user", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
