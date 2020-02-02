package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.UserRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Role;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public void registerUser(final User user) {
    final Set<Role> defaultRoles = new HashSet<>();
    defaultRoles.add(Role.USER);
    user.setRoles(defaultRoles);

    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public List<User> listUsers() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public UserDetails findUserByUsername(final String username) {
    return userRepository.findUserByUsername(username);
  }

  @Transactional(readOnly = true)
  public User findUserById(final Long userId) {
    return userRepository.findUserById(userId);
  }

  public boolean isUserNameTaken(final String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean userExistsById(final Long id) {
    return userRepository.existsById(id);
  }
}
