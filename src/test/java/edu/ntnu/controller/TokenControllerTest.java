package edu.ntnu.controller;

import edu.ntnu.model.LoginRequest;
import edu.ntnu.service.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenControllerTest {

  @Mock
  private SecurityService securityService;

  @InjectMocks
  private TokenController tokenController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(tokenController).build();
  }

  @Test
  void testGenerateToken() throws Exception {
    LoginRequest loginRequest = new LoginRequest("testUser", "testPassword");

    when(securityService.correctPassword(loginRequest.getUsername(), loginRequest.getPassword())).thenReturn(true);

    mockMvc.perform(post("/token/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"testUser\", \"password\":\"testPassword\"}"))
            .andExpect(status().isCreated());
  }
}