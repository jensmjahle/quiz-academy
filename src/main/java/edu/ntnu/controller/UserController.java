package edu.ntnu.controller;

import edu.ntnu.dto.UserDTO;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.ntnu.repositories.UserRepository;
import edu.ntnu.entities.User;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

  Logger logger = Logger.getLogger(UserController.class.getName());

  private final ModelMapper modelMapper = new ModelMapper();
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{username}")
  public ResponseEntity getUserByUsername(@PathVariable String username) {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Create a new user
   *
   * @param userDTO UserDTO object
   * @return ResponseEntity<String>
   */

  @PostMapping()
  public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
    logger.info("Creating user");
    User user = modelMapper.map(userDTO, User.class);

    if (userRepository.findByUsername(user.getUsername()) != null) {
      return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
    } else {
      try {
        userRepository.save(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  }

}
