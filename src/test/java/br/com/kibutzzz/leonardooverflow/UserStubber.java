package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;

public class UserStubber {


  public static User generateCompleteUser() {

    return User.builder()
      .id(1L)
      .username("USERNAME")
      .password("PASSWORD")
      .roles(RoleStubber.allRoles())
      .build();
  }
}
