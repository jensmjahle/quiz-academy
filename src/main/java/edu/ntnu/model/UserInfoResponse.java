package edu.ntnu.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponse {

  final private String firstName;
  final private String lastName;
  final private String gender;

  @JsonIgnore
  private String username;
  @JsonIgnore
  private String password;

  public UserInfoResponse(@JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("gender") String gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
  }

  public UserInfoResponse(@JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("gender") String gender,
      final String username,
      final String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
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

  @JsonProperty("gender")
  public String getGender() {
    return gender;
  }

  public String getUsername() {return username;}

  public String getPassword() {return password;}
}