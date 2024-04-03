package edu.ntnu.mapper;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.dao.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserDAO userDAO) {
        return new UserDTO(
            userDAO.getUsername(),
            userDAO.getPassword(),
            userDAO.getEmail(),
            userDAO.getFirstName(),
            userDAO.getLastName());
    }
  public UserDAO toDAO(UserDTO userDTO) {
    return new UserDAO(
        userDTO.getUsername(),
        userDTO.getPassword(),
        userDTO.getEmail(),
        userDTO.getFirstName(),
        userDTO.getLastName());
  }
}
