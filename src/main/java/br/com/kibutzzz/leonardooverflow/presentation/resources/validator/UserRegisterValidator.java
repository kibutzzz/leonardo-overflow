package br.com.kibutzzz.leonardooverflow.presentation.resources.validator;

import br.com.kibutzzz.leonardooverflow.application.UserService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class UserRegisterValidator implements Validator {

  private final UserService userService;

  @Override
  public boolean supports(final Class<?> clazz) {
    return CreateUserRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {

    if (errors.hasErrors()) {
      return;
    }

    final CreateUserRequest request = (CreateUserRequest) target;
    if (!request.getPassword().equals(request.getPasswordConfirmation())) {
      errors.rejectValue("password", "validation.user.create.passwordMismatch");
    }

    if (userService.isUserNameTaken(request.getUsername())) {
      errors.rejectValue("username", "validation.user.create.usernameTaken");
    }

  }
}
