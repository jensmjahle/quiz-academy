package edu.ntnu.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.ntnu.dto.UserDTO;

public class RefreshRequest {
  UserDTO userDTO;
  String token;


  @JsonCreator
  public RefreshRequest(@JsonProperty("userDTO") final UserDTO userDTO, @JsonProperty("token") final String token) {
    this.userDTO = userDTO;
    this.token = token;
  }

  @JsonProperty("userDTO")
  public UserDTO getUserDTO() {
    return userDTO;
  }

  @JsonProperty("token")
  public String getToken() {
    return token;
  }

}
