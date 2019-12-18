package br.com.kibutzzz.leonardooverflow.presentation.resources.validator;

import br.com.kibutzzz.leonardooverflow.CreateUserRequestStubber;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@NoArgsConstructor
public class UserRegisterValidatorTest {

    @InjectMocks
    private UserRegisterValidator validator;

    private Errors errors;

    @Test
    public void test_supports_withAssignableClass_shouldReturnTrue() {

        assertTrue(validator.supports(CreateUserRequest.class));

    }

    @Test
    public void test_supports_withNotAssignableClass_shouldReturnFalse() {

        assertFalse(validator.supports(Object.class));

    }

    @Test
    public void test_validate_withValidObject_shouldAddErrors() {

        CreateUserRequest target = CreateUserRequestStubber.generateValidCreateUserRequest();

        errors = new BeanPropertyBindingResult(target, "validRequest");

        validator.validate(target, errors);

        assertFalse(errors.hasErrors());

    }

    @Test
    public void test_validate_withPreviousValidationError_shouldNotAddNewError() {

        CreateUserRequest target = CreateUserRequestStubber.generateInvalidCreateUserRequest();

        errors = new BeanPropertyBindingResult(target, "validRequest");
        errors.rejectValue("username","UserNameBlank");
        validator.validate(target, errors);

        assertEquals(1, errors.getErrorCount());

    }

    @Test
    public void test_validate_withoutMatchingPasswords_shouldAddCorrectError() {

        CreateUserRequest target = CreateUserRequestStubber.generateInvalidCreateUserRequest();

        errors = new BeanPropertyBindingResult(target, "validRequest");

        validator.validate(target, errors);

        assertEquals("validation.user.create.passwordMismatch", errors.getAllErrors().get(0).getCode());


    }

}