package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedTagResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.TagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface TagMapper {

  @Mapping(target = "id", ignore = true)
  Tag fromRequest(CreateTagRequest tagRequest);

  TagResponse toResponse(Tag tag);

  List<TagResponse> toResponse(List<Tag> tags);

  SimplifiedTagResponse toSimplifiedResponse(Tag tag);

  List<SimplifiedTagResponse> toSimplifiedResponse(List<Tag> tag);
}
