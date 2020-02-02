package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.UserService;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.UserMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedUserResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.validator.UserRegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final UserRegisterValidator registerValidator;

  private final UserMapper userMapper;

  @InitBinder("createUserRequest")
  public void setUserRegisteringValidator(final DataBinder binder) {
    binder.addValidators(registerValidator);
  }

  @PostMapping
  public ResponseEntity<Void> registerUser(@Valid @RequestBody final CreateUserRequest request) {
    userService.registerUser(userMapper.fromRequest(request));
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public List<SimplifiedUserResponse> listUsers() {
    return userMapper.toResponse(userService.listUsers());
  }

  @GetMapping("/validate/{username}")
  public ResponseEntity<Void> validateUsername(@PathVariable final String username) {
    if (userService.isUserNameTaken(username)) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok().build();
  }

}
