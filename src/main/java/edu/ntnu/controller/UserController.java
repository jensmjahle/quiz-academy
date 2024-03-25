package edu.ntnu.controller;

import edu.ntnu.dto.UserDTO;
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
import edu.ntnu.repository.UserRepository;
import edu.ntnu.entity.User;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

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
    System.out.println(userDTO.toString());
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    user.setEmail(userDTO.getEmail());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());

    userRepository.save(user);

    return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
  }

}
