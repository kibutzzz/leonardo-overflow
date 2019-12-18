package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;

public class CreateUserRequestStubber {
    public static CreateUserRequest generateValidCreateUserRequest() {
        return CreateUserRequest.builder()
                .password("randomPassword")
                .passwordConfirmation("randomPassword")
                .username("newUser")
                .build();
    }

    public static CreateUserRequest generateInvalidCreateUserRequest() {
        return CreateUserRequest.builder()
                .password("kabkabakb")
                .passwordConfirmation("blablabla")
                .username("newUser")
                .build();
    }
}
