package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    User fromRequest(CreateUserRequest request);

    List<SimplifiedUserResponse> toResponse(List<User> listUsers);

    SimplifiedUserResponse toSimplifiedResponse(User user);

}
