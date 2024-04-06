package edu.ntnu.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ntnu.security.JWTAuthorizationFilter;
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

/**
 * Controller for handling JWT tokens.
 */
@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin(origins = "*") //todo change to frontend url
public class TokenController {
  Logger logger = Logger.getLogger(TokenController.class.getName());
  SecurityService securityService;

  private static String secretKey = null;

  private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(6);

  @Autowired
  public TokenController(SecurityService securityService) {
    this.securityService = securityService;
  }

  /**
   * Generate a JWT token for the given user.
   * @param loginRequest the login request containing the username and password
   * @return the generated token
   * @throws Exception if the username and password are invalid
   */
  @PostMapping(value = "/new")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {
    logger.info("Received request to generate token for user: " + loginRequest.getUsername() + ".");
    if (securityService.correctPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
      String generatedToken = generateToken(loginRequest.getUsername());
      return generatedToken;
    }
    logger.warning("Access denied, wrong credentials....");

    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
  }

  /**
   * Refresh the JWT token.
   * @param token the token to be exchanged for a new token to be given to the user
   * @return the refreshed token
   */
  @PostMapping(value = "/refresh")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String refreshToken(@RequestBody String token) {
    logger.info("Received request to refresh token.");

    try {
      final Algorithm hmac512 = Algorithm.HMAC512(getSecretKey());
      JWTVerifier verifier = JWT.require(hmac512)
              .withIssuer("idatt2105_token_issuer_app")
              .build();

      JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter();
      String userid = jwtAuthorizationFilter.validateTokenAndGetUserId(token);

      return generateToken(userid);

    } catch (Exception e) {
      logger.warning("Access denied, wrong credentials....");
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }
  }

  /**
   * Delete the secret key used to sign the JWT token.
   */
  @PostMapping(value = "/delete")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteToken() {

    logger.info("Received request to delete token.");
    secretKey = null;
  }

  /**
   * Generate a JWT token for the given user id.
   * @param userId the user id
   * @return the generated token
   */
  private String generateToken(final String userId) {
    logger.info("Generating token for user: " + userId + ".");
    final Instant now = Instant.now();
    //System.out.println("secret"+keyStr);
    final Algorithm hmac512 = Algorithm.HMAC512(getSecretKey());
    return JWT.create()
        .withSubject(userId)
        .withIssuer("idatt2105_token_issuer_app")
        .withIssuedAt(now)
        .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
        .sign(hmac512);
  }

  /**
   * Generate a random secret key and encode it to a Base64 string.
   * @return the secret key
   */
  private static String generateRandomSecretKey() {
    // Generate a random secret key
    if (TokenController.secretKey == null) {
      SecureRandom random = new SecureRandom();
      byte[] key = new byte[64];
      random.nextBytes(key);
      TokenController.secretKey = Base64.getEncoder().encodeToString(key);
    }
    return TokenController.secretKey;
  }

  /**
   * Get the secret key used to sign the JWT token.
   * If the secret key is not set, go ahead and generate a random secret key.
   * @return the secret key
   */
  public static String getSecretKey () {
    if (secretKey == null) {
      return generateRandomSecretKey();
    }
    return secretKey;
  }


}

