package edu.ntnu.controller;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ntnu.service.SecurityService;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import edu.ntnu.model.LoginRequest;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:5173")

public class TokenController {
  Logger logger = Logger.getLogger(TokenController.class.getName());
  SecurityService securityService;

  

  //Will try to get secret key from environment variable, if not found, generate a random one
  public static final String keyStr = System.getenv("SECRET_KEY") != null ? System.getenv("SECRET_KEY") :generateRandomSecretKey();
  private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

  @Autowired
  public TokenController(SecurityService securityService) {
    this.securityService = securityService;
  }


  @PostMapping(value = "")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {
    logger.info("Received request to generate token for user: " + loginRequest.getUsername() + ".");
    // if username and password are valid, issue an access token
    // note that subsequent requests need this token
    if (securityService.correctPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
      return generateToken(loginRequest.getUsername());
    }
    logger.warning("Access denied, wrong credentials....");

    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
  }

  public String generateToken(final String userId) {
    logger.info("Generating token for user: " + userId + ".");
    final Instant now = Instant.now();
    System.out.println(keyStr);
    final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
    final JWTVerifier verifier = JWT.require(hmac512).build();
    return JWT.create()
        .withSubject(userId)
        .withIssuer("idatt2105_token_issuer_app")
        .withIssuedAt(now)
        .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
        .sign(hmac512);
  }

  private static String generateRandomSecretKey() {
    // Generate a random secret key
    SecureRandom random = new SecureRandom();
    byte[] key = new byte[64];
    random.nextBytes(key);
    return Base64.getEncoder().encodeToString(key);
  }
}

