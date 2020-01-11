package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.QuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedQuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SpecificQuestionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        config = BaseMapperConfig.class,
        uses = {VoteMapper.class, CommentMapper.class, AnswerMapper.class, UserMapper.class, TagMapper.class}
)
public interface QuestionMapper {

    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "answers", ignore = true)
    Question fromRequest(CreateQuestionRequest request);

    QuestionResponse toResponse(Question question);

    @Mapping(target = "voteCount", source = "votes", qualifiedByName = {"VoteMapper", "countVoteTotals"})
    SpecificQuestionResponse toSpecificQuestionResponse(Question question);

    List<SimplifiedQuestionResponse> toSimplifiedQuestionResponse(List<Question> question);

    @Mapping(target = "voteCount", source = "votes", qualifiedByName = {"VoteMapper", "countVoteTotals"})
    SimplifiedQuestionResponse toSimplifiedQuestionResponse(Question question);

}
