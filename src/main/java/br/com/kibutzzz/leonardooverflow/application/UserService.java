package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.UserRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Role;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import lombok.RequiredArgsConstructor;
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
    public void registerUser(User user) {
        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(Role.USER);
        user.setRoles(defaultRoles);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
