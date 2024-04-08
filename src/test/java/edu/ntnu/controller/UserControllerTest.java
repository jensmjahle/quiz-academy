package edu.ntnu.controller;

import edu.ntnu.dto.UserDTO;
import edu.ntnu.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void testGetUserByUsername() throws Exception {
    String username = "testUser";
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(username);

    when(userService.getUserByUsername(username)).thenReturn(ResponseEntity.ok(userDTO));

    mockMvc.perform(get("/users/" + username)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void testCreateUser() throws Exception {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("testUser");
    userDTO.setPassword("testPassword");

    when(userService.createUser(userDTO)).thenReturn(ResponseEntity.ok("User created successfully"));

    mockMvc.perform(post("/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"testUser\", \"password\":\"testPassword\"}"))
            .andExpect(status().isOk());
  }

  @Test
  void testDeleteUser() throws Exception {
    String username = "testUser";

    when(userService.deleteUser(username)).thenReturn(ResponseEntity.ok("User deleted successfully"));

    mockMvc.perform(delete("/users/delete/" + username)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}