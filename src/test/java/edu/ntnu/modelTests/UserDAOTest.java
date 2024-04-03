package edu.ntnu.modelTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.dao.UserDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Tests")
public class UserDAOTest {

  @Test
  @DisplayName("User is correctly instantiated")
  public void testUserInstantiation() {
    // Arrange
    String username = "testuser";
    String password = "testpassword";
    String email = "test@example.com";
    String firstName = "Test";
    String lastName = "User";

    // Act
    UserDAO userDAO = new UserDAO(username, password, email, firstName, lastName);

    // Assert
    assertThat(userDAO).isNotNull();
    assertThat(userDAO.getUsername()).isEqualTo(username);
    assertThat(userDAO.getPassword()).isEqualTo(password);
    assertThat(userDAO.getEmail()).isEqualTo(email);
    assertThat(userDAO.getFirstName()).isEqualTo(firstName);
    assertThat(userDAO.getLastName()).isEqualTo(lastName);
  }

  @Test
  @DisplayName("User setters and getters work correctly")
  public void testUserSettersAndGetters() {
    // Arrange
    UserDAO userDAO = new UserDAO();
    String username = "testuser";
    String password = "testpassword";
    String email = "test@example.com";
    String firstName = "Test";
    String lastName = "User";

    // Act
    userDAO.setUsername(username);
    userDAO.setPassword(password);
    userDAO.setEmail(email);
    userDAO.setFirstName(firstName);
    userDAO.setLastName(lastName);

    // Assert
    assertThat(userDAO.getUsername()).isEqualTo(username);
    assertThat(userDAO.getPassword()).isEqualTo(password);
    assertThat(userDAO.getEmail()).isEqualTo(email);
    assertThat(userDAO.getFirstName()).isEqualTo(firstName);
    assertThat(userDAO.getLastName()).isEqualTo(lastName);
  }
}
