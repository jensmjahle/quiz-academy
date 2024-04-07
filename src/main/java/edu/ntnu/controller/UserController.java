package edu.ntnu.controller;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user requests.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Get a user by username.
   *
   * @param username the username of the user
   * @return the user with the given username
   */
  @GetMapping("/{username}")
  public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
    return userService.getUserByUsername(username);
  }

  /**
   * Create a new user.
   *
   * @param userDTO the user to create
   * @return the response entity
   */
@PostMapping("/create")
  public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
    return userService.createUser(userDTO);
  }

  @DeleteMapping("/delete/{username}")
  public ResponseEntity<String> deleteUser(@PathVariable String username) {
    return userService.deleteUser(username);
  }
}
