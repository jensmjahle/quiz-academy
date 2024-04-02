package edu.ntnu.modelTests;


import static org.assertj.core.api.Assertions.assertThat;

import edu.ntnu.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Tests")
public class UserTest {

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
    User user = new User(username, password, email, firstName, lastName);

    // Assert
    assertThat(user).isNotNull();
    assertThat(user.getUsername()).isEqualTo(username);
    assertThat(user.getPassword()).isEqualTo(password);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getFirstName()).isEqualTo(firstName);
    assertThat(user.getLastName()).isEqualTo(lastName);
  }

  @Test
  @DisplayName("User setters and getters work correctly")
  public void testUserSettersAndGetters() {
    // Arrange
    User user = new User();
    String username = "testuser";
    String password = "testpassword";
    String email = "test@example.com";
    String firstName = "Test";
    String lastName = "User";

    // Act
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    user.setFirstName(firstName);
    user.setLastName(lastName);

    // Assert
    assertThat(user.getUsername()).isEqualTo(username);
    assertThat(user.getPassword()).isEqualTo(password);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getFirstName()).isEqualTo(firstName);
    assertThat(user.getLastName()).isEqualTo(lastName);
  }
}
