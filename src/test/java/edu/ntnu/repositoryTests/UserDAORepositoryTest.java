package edu.ntnu.repositoryTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import edu.ntnu.dao.UserDAO;
import edu.ntnu.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
@DisplayName("User Repository Tests")
public class UserDAORepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("User is saved to the database correctly")
  public void testSaveUser() {
    // Create a user
    UserDAO userDAO = new UserDAO("username", "password", "email", "firstName", "lastName");

    // Save the user to the database
    UserDAO savedUserDAO = userRepository.save(userDAO);

    // Retrieve the user from the database
    UserDAO retrievedUserDAO = entityManager.find(UserDAO.class, savedUserDAO.getUsername());

    // Check that the user was saved correctly
    assertThat(retrievedUserDAO.getUsername()).isEqualTo(userDAO.getUsername());
  }

  @Test
  @DisplayName("User is deleted from the database correctly")
  public void testDeleteUser() {
    // Create a user
    UserDAO userDAO = new UserDAO("username2", "password", "email", "firstName", "lastName");
    entityManager.persist(userDAO);

    // Delete the user from the repository
    userRepository.delete(userDAO);

    // Check that the user was deleted successfully
    assertThat(userRepository.findByUsername(userDAO.getUsername())).isNull();
  }

  @Test
@DisplayName("User is retrieved from the database correctly")
  public void testGetUser() {
    // Create a user
    UserDAO userDAO = new UserDAO("username", "password", "email", "firstName", "lastName");
    entityManager.persist(userDAO);

    // Retrieve the user from the repository
    UserDAO retrievedUserDAO = userRepository.findByUsername(userDAO.getUsername());

    // Check that the user was retrieved successfully
    assertThat(retrievedUserDAO.getUsername()).isEqualTo(userDAO.getUsername());
  }
}

