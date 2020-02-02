package br.com.kibutzzz.leonardooverflow.infrastructure.configuration.security;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@NoArgsConstructor
public class TokenService {

  @Value("${jwt.expiration}")
  private String expiration;

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(final Authentication auth) {
    final User usuarioLogado = (User) auth.getPrincipal();
    final Date today = new Date();

    final Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
    return Jwts.builder()
      .setIssuer("Leonardo Overflow API")
      .setSubject(usuarioLogado.getId().toString())
      .setIssuedAt(today)
      .setExpiration(expirationDate)
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
  }

  public boolean isTokenValid(final String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (final Exception e) {
      return false;
    }
  }

  public Long getUserId(final String token) {
    final Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return Long.parseLong(body.getSubject());
  }
}
