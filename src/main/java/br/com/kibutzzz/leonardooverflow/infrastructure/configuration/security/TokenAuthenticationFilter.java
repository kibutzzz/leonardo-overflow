package br.com.kibutzzz.leonardooverflow.infrastructure.configuration.security;

import br.com.kibutzzz.leonardooverflow.application.UserService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  private final UserService userService;

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                  final FilterChain filterChain)
    throws ServletException, IOException {

    final String token = getToken(request);

    final boolean isValid = tokenService.isTokenValid(token);
    if (isValid) {
      final Long userId = tokenService.getUserId(token);
      final User user = userService.findUserById(userId);
      final UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String getToken(final HttpServletRequest request) {
    final String token = request.getHeader("Authorization");
    if (isNull(token) || token.isEmpty() || !token.startsWith("Bearer")) {
      return null;
    }
    return token.substring(7);
  }
}
