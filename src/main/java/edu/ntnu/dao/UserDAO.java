package edu.ntnu.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a user in the system.
 */
@Entity
public class UserDAO {
  @Id
  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false)
  private String password;
  private String email;
  private String firstName;
  private String lastName;

  /**
   * Creates a new instance of the User class.
   */
  public UserDAO() {
  }
  public UserDAO(String username, String password, String email, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the username of the user.
   *
   * @return the username of the user
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the user.
   *
   * @param username the username of the user
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Returns the password of the user.
   *
   * @return the password of the user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   *
   * @param password the password of the user
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the email of the user.
   *
   * @return the email of the user
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the user.
   *
   * @param email the email of the user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns the first name of the user.
   *
   * @return the first name of the user
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the user.
   *
   * @param firstName the first name of the user
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Returns the last name of the user.
   *
   * @return the last name of the user
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the user.
   *
   * @param lastName the last name of the user
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
