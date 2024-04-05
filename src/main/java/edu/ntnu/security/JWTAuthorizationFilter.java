package edu.ntnu.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.ntnu.controller.TokenController;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Filter for JWT authorization.
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LogManager.getLogger(JWTAuthorizationFilter.class);

  public static final String USER = "USER";
  public static final String ROLE_USER = "ROLE_" + USER;

  /**
   * Filter method for JWT authorization.
   * @param request the HTTP request
   * @param response the HTTP response
   * @param filterChain the filter chain
   * @throws ServletException if an error occurs
   * @throws IOException if an error occurs
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // check Bearer auth header
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    // if Bearer auth header exists, validate token, and extract userId from token.
    // Note that we have added userId as subject to the token when it is generated
    // Note also that the token comes in this format 'Bearer token'
    String token = header.substring(7);
    final String username = validateTokenAndGetUserId(token);
    if (username == null) {
      // validation failed or token expired
      filterChain.doFilter(request, response);
      return;
    }

    // if token is valid, add user details to the authentication context
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        username,
        null,
        Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    SecurityContextHolder.getContext().setAuthentication(auth);

    // then, continue with authenticated user context
    filterChain.doFilter(request, response);
  }


  /**
   * Validate the token and extract the userId from it.
   * @param token the token to validate
   * @return the userId extracted from the token
   */
  public String validateTokenAndGetUserId(final String token) {
    // remove quotes from token as it breaks the verification
    String tokenWithoutQuotes = token.replace("\"", "");
    try {
      final Algorithm hmac512 = Algorithm.HMAC512(TokenController.getSecretKey());
      final JWTVerifier verifier = JWT.require(hmac512).build();
      return verifier.verify(tokenWithoutQuotes).getSubject();
    } catch (final JWTVerificationException verificationEx) {
      LOGGER.warn("token is invalid: {}", verificationEx.getMessage());
      return null;
    }
  }

}
