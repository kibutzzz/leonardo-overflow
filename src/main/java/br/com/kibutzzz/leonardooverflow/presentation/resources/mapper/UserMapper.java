package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateUserRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface UserMapper {

  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "authorities", ignore = true)
  User fromRequest(CreateUserRequest request);

  List<SimplifiedUserResponse> toResponse(List<User> listUsers);

  SimplifiedUserResponse toSimplifiedResponse(User user);

}
