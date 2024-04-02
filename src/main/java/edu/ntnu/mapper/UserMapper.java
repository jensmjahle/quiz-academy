package edu.ntnu.mapper;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName());
    }
  public User toUser(UserDTO userDTO) {
    return new User(
        userDTO.getUsername(),
        userDTO.getPassword(),
        userDTO.getEmail(),
        userDTO.getFirstName(),
        userDTO.getLastName());
  }
}
