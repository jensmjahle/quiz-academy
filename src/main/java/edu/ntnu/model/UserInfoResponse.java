package edu.ntnu.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class for user information responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponse {

  final private String firstName;
  final private String lastName;
  final private String email;

  @JsonIgnore
  private String username;
  @JsonIgnore
  private String password;

  public UserInfoResponse(@JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("email") String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public UserInfoResponse(@JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("email") String email,
      final String username,
      final String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
  }

  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public String getUsername() {return username;}

  public String getPassword() {return password;}
}