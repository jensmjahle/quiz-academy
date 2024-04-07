package edu.ntnu.mapper;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.dao.UserDAO;
import org.springframework.stereotype.Component;

/**
 * Mapper class for the User entity.
 */
@Component
public class UserMapper {

  /**
   * Maps a UserDAO object to a UserDTO object.
   * @param userDAO The UserDAO object to map.
   * @return The UserDTO object.
   */
  public UserDTO toDTO(UserDAO userDAO) {
      return new UserDTO(
          userDAO.getUsername(),
          userDAO.getPassword(),
          userDAO.getEmail(),
          userDAO.getFirstName(),
          userDAO.getLastName());
  }

  /**
   * Maps a UserDTO object to a UserDAO object.
   * @param userDTO The UserDTO object to map.
   * @return The UserDAO object.
   */
  public UserDAO toDAO(UserDTO userDTO) {
    return new UserDAO(
        userDTO.getUsername(),
        userDTO.getPassword(),
        userDTO.getEmail(),
        userDTO.getFirstName(),
        userDTO.getLastName());
  }
}
