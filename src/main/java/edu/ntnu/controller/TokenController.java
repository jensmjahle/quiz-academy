package edu.ntnu.controller;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.ntnu.dto.UserDTO;
import edu.ntnu.model.RefreshRequest;
import edu.ntnu.security.JWTAuthorizationFilter;
import edu.ntnu.service.SecurityService;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;

import edu.ntnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import edu.ntnu.model.LoginRequest;
import edu.ntnu.security.JWTAuthorizationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;


import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin(origins = "*")

public class TokenController {
  Logger logger = Logger.getLogger(TokenController.class.getName());
  SecurityService securityService;

  private static String secretKey = null;

  //Will try to get secret key from environment variable, if not found, generate a random one
  public static String keyStr = System.getenv("SECRET_KEY") != null ? System.getenv("SECRET_KEY") : generateRandomSecretKey();
  private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

  @Autowired
  public TokenController(SecurityService securityService) {
    this.securityService = securityService;
  }

  @PostMapping(value = "/new")
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

  @PostMapping(value = "/refresh")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String refreshToken(@RequestBody String token) {
    logger.info("Received request to refresh token.");
    logger.info("Token: " + token);
    try {
      logger.info("1");
      final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
      logger.info("2");
      JWTVerifier verifier = JWT.require(hmac512)
              .withIssuer("idatt2105_token_issuer_app")
              .build();
      // Verify the token
      logger.info("3");
      System.out.println(token);

      Claims claims = Jwts.parser().setSigningKey(keyStr).parseClaimsJws(token).getBody();
      System.out.println("Subject: " + claims.getSubject());
      System.out.println("Issuer: " + claims.getIssuer());
      System.out.println("Expiration: " + claims.getExpiration());
      System.out.println("Issued At: " + claims.getIssuedAt());


      //logger.info("User ID: " + userId);
      logger.info("4");
      // Generate a new token with extended expiration time

      //return generateToken(userId);
    } catch (Exception e) {
      logger.warning("Access denied, wrong credentials....");
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }
    return null;
  }

  @PostMapping(value = "/delete")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteToken() {
    keyStr = null;
  }

  private String generateToken(final String userId) {
    logger.info("Generating token for user: " + userId + ".");
    final Instant now = Instant.now();
    System.out.println(keyStr);
    final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
    return JWT.create()
        .withSubject(userId)
        .withIssuer("idatt2105_token_issuer_app")
        .withIssuedAt(now)
        .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
        .sign(hmac512);
  }

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

}

