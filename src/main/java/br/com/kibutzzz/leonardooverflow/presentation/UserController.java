package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.UserService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.UserMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedUserResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.validator.UserRegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRegisterValidator registerValidator;

    @InitBinder("createUserRequest")
    public void setUserRegisteringValidator(DataBinder binder) {
        binder.addValidators(registerValidator);
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody CreateUserRequest request) {
        userService.registerUser(UserMapper.INSTANCE.fromRequest(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<SimplifiedUserResponse> listUsers() {
        return UserMapper.INSTANCE.toResponse(userService.listUsers());
    }

    @GetMapping("/validate/{username}")
    public ResponseEntity<Void> validateUsername(@PathVariable String username) {
        if (userService.isUserNameTaken(username)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
