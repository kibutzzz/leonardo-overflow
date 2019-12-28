package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@NoArgsConstructor
class UserMapperTest {

    private static final CreateUserRequest NULL_CREATE_USER_REQUEST = null;
    private static final List<User> NULL_USER_LIST = null;

    @Test
    public void test_fromRequest_withNullArgument_shouldReturnNull() {

        assertNull(UserMapper.INSTANCE.fromRequest(NULL_CREATE_USER_REQUEST));
    }

    @Test
    public void test_toResponse_withNullArgument_shouldReturnNull() {

        assertNull(UserMapper.INSTANCE.toResponse(NULL_USER_LIST));
    }

}