package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Tag;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateTagRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(target = "id", ignore = true)
    Tag fromRequest(CreateTagRequest tagRequest);
}
