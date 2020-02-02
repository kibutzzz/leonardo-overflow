package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiErrorResponse;
import br.com.kibutzzz.leonardooverflow.infrastructure.configuration.security.TokenService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.LoginRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> authenticate(@Valid @RequestBody final LoginRequest request) {
    final UsernamePasswordAuthenticationToken authentication =
      new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

    final Authentication auth = authenticationManager.authenticate(authentication);
    final String token = tokenService.generateToken(auth);

    return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiErrorResponse> handleAuthenticationException(final AuthenticationException ex) {
    return ResponseEntity.badRequest().body(new ApiErrorResponse(ex.getMessage()));
  }

}
