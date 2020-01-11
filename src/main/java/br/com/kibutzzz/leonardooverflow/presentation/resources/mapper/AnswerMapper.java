package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.AnswerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = BaseMapperConfig.class, uses = {VoteMapper.class, CommentMapper.class})
public interface AnswerMapper {


    @Mapping(target = "voteCount", source = "votes", qualifiedByName = {"VoteMapper", "countVoteTotals"})
    AnswerResponse toResponse(Answer answer);

    List<AnswerResponse> toResponse(List<Answer> answers);

}