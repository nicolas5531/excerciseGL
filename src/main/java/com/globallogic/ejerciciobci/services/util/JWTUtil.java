package com.globallogic.ejerciciobci.services.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.globallogic.ejerciciobci.controllers.exceptions.InvalidTokenException;
import com.globallogic.ejerciciobci.repositories.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Configuration
public class JWTUtil {
  @Value("${security.secret}")
  private String secret;

  public String createTokenWithShortExpiration(User user) {
    Algorithm algorithm = Algorithm.HMAC256(getSecret());

    return JWT.create()
        .withJWTId(UUID.randomUUID().toString())
        .withIssuer("http://localhost:8080")
        .withSubject(user.getEmail())
        .withIssuedAt(Instant.now())
        .withNotBefore(Instant.now())
        .withExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
        .sign(algorithm);
  }

  public String verify(String token) {
    Algorithm algorithm = Algorithm.HMAC256(getSecret());

    JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer("http://localhost:8080")
        .build();

    try {
      DecodedJWT decodedJWT = verifier.verify(token);
      return decodedJWT.getSubject();
    } catch (JWTVerificationException exception) {
      throw new InvalidTokenException(exception);
    }
  }

  String getSecret() {
    return secret != null
        ? secret
        : "test";
  }
  public void setSecret(String secret) {
    this.secret = secret;
  }
}
