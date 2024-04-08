package edu.ntnu.ModelTests;

import edu.ntnu.model.UserInfoResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInfoResponseTest {

  @Test
  void testGetters() {
    String firstName = "John";
    String lastName = "Doe";
    String email = "john.doe@example.com";
    String username = "johndoe";
    String password = "password123";

    UserInfoResponse userInfoResponse = new UserInfoResponse(firstName, lastName, email, username, password);

    assertEquals(firstName, userInfoResponse.getFirstName());
    assertEquals(lastName, userInfoResponse.getLastName());
    assertEquals(email, userInfoResponse.getEmail());
    assertEquals(username, userInfoResponse.getUsername());
    assertEquals(password, userInfoResponse.getPassword());
  }
}