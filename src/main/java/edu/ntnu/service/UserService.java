package edu.ntnu.service;

import edu.ntnu.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import edu.ntnu.entities.User;
import edu.ntnu.repositories.UserRepository;
import java.util.logging.Logger;

/**
 * Service class for User objects.
 */
@Service
public class UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper = new ModelMapper();
  private final Logger logger = Logger.getLogger(UserService.class.getName());

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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
  public ResponseEntity<User> getUserByUsername(String username) {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
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
      User user = modelMapper.map(userDTO, User.class);
      logger.info("User object created from userDTO");

      if (userRepository.findByUsername(user.getUsername()) != null) {
        logger.info("No user created, username already exists");
        return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
      } else {
        try {
          userRepository.save(user);
          logger.info("User " + user.getUsername() + " created successfully");
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
}
