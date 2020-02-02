package br.com.kibutzzz.leonardooverflow.infrastructure.configuration.security;

import br.com.kibutzzz.leonardooverflow.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final UserDetails user = userService.findUserByUsername(username);
    if (isNull(user)) {
      throw new UsernameNotFoundException("Invalid Credentials!");
    }
    return user;
  }
}
