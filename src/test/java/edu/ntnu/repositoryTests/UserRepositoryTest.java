package edu.ntnu.repositoryTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.ntnu.model.User;
import edu.ntnu.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
@DisplayName("User Repository Tests")
public class UserRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("User is saved to the database correctly")
  public void testSaveUser() {
    // Create a user
    User user = new User("username", "password", "email", "firstName", "lastName");

    // Save the user to the database
    User savedUser = userRepository.save(user);

    // Retrieve the user from the database
    User retrievedUser = entityManager.find(User.class, savedUser.getUsername());

    // Check that the user was saved correctly
    assertThat(retrievedUser.getUsername()).isEqualTo(user.getUsername());
  }

  @Test
  @DisplayName("User is deleted from the database correctly")
  public void testDeleteUser() {
    // Create a user
    User user = new User("username2", "password", "email", "firstName", "lastName");
    entityManager.persist(user);

    // Delete the user from the repository
    userRepository.delete(user);

    // Check that the user was deleted successfully
    assertThat(userRepository.findByUsername(user.getUsername())).isNull();
  }

  @Test
@DisplayName("User is retrieved from the database correctly")
  public void testGetUser() {
    // Create a user
    User user = new User("username", "password", "email", "firstName", "lastName");
    entityManager.persist(user);

    // Retrieve the user from the repository
    User retrievedUser = userRepository.findByUsername(user.getUsername());

    // Check that the user was retrieved successfully
    assertThat(retrievedUser.getUsername()).isEqualTo(user.getUsername());
  }
}

