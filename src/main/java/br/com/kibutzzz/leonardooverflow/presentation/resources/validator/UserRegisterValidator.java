package br.com.kibutzzz.leonardooverflow.presentation.resources.validator;

import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;

@Component
public class UserRegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateUserRequest request = (CreateUserRequest) target;

        if(errors.hasErrors()) {
            return;
        }

        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            errors.rejectValue("password", "validation.user.create.passwordMismatch");
        }

        //TODO add unique user validation

    }
}
