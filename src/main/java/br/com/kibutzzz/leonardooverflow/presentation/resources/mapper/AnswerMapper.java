package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.AnswerRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.AnswerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Named("AnswerMapper")
@Mapper(config = BaseMapperConfig.class, uses = {VoteMapper.class, CommentMapper.class, UserMapper.class})
public interface AnswerMapper {

  @Named("countAnswers")
  default Long countAnswers(List<Answer> answers) {
    return (long) answers.size();
  }

  @Mapping(target = "voteCount", source = "votes", qualifiedByName = {"VoteMapper", "countVoteTotals"})
  AnswerResponse toResponse(Answer answer);

  @Named("toResponse")
  List<AnswerResponse> toResponse(List<Answer> answers);

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "votes", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Answer fromRequest(AnswerRequest request);

}
