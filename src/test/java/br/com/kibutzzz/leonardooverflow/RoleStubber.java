package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleStubber {
    public static Set<Role> allRoles() {

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        roles.add(Role.USER);

        return roles;
    }
}
