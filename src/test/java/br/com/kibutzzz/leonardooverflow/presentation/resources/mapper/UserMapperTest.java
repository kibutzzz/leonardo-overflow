package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@NoArgsConstructor
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = UserMapperImpl.class
)
class UserMapperTest {

    private static final CreateUserRequest NULL_CREATE_USER_REQUEST = null;
    private static final List<User> NULL_USER_LIST = null;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_fromRequest_withNullArgument_shouldReturnNull() {

        assertNull(userMapper.fromRequest(NULL_CREATE_USER_REQUEST));
    }

    @Test
    public void test_toResponse_withNullArgument_shouldReturnNull() {

        assertNull(userMapper.toResponse(NULL_USER_LIST));
    }

}