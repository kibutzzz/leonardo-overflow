package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.config.BaseMapperConfig;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateCommentRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = BaseMapperConfig.class, uses = {VoteMapper.class})
public interface CommentMapper {

  @Mapping(target = "voteCount", source = "votes", qualifiedByName = {"VoteMapper", "countVoteTotals"})
  CommentResponse toResponse(Comment comment);

  List<CommentResponse> toResponse(List<Comment> comments);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "votes", ignore = true)
  Comment fromRequest(CreateCommentRequest commentRequest);
}
