package edu.ntnu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Data transfer object for the User entity.
 */
public class UserDTO {
  private String username;
  @JsonIgnore
  private String password;
  private String email;
  private String firstName;
  private String lastName;

  /**
   * Constructor for the UserDTO class.
   */
  public UserDTO() {
  }

  /**
   * Constructor for the UserDTO class.
   * @param username the username of the user
   * @param password the password of the user
   * @param email the email of the user
   * @param firstName the first name of the user
   * @param lastName the last name of the user
   */
  public UserDTO(String username, String password, String email, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Getter for the username.
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Setter for the username.
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Getter for the password.
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter for the password.
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Getter for the email.
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for the email.
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Getter for the first name.
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Setter for the first name.
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Getter for the last name.
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Setter for the last name.
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Returns a string representation of the UserDTO object.
   * @return a string representation of the UserDTO object
   */
  @Override
  public String toString() {
    return "UserDTO{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}

