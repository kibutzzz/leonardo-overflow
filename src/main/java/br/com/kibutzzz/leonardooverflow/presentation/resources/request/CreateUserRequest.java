package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String passwordConfirmation;

}
