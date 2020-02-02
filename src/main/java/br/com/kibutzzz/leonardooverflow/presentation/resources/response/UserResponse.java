package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {

  private Long id;

  private String username;

  private Set<Role> roles;


}
