package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.VoteRepositoty;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Voteable;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.VoteEntity;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.VoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoteService {

  private final VoteRepositoty voteRepositoty;

  private final QuestionService questionService;

  private final AnswerService answerService;

  private final CommentService commentService;

  @Transactional
  public void castVote(final VoteRequest request, final User user) {

    final Voteable voteable = getVoteable(request);

    final Vote vote = voteable.getVotes().stream()
      .filter(v -> user.equals(v.getUser()))
      .findFirst().orElse(Vote.builder().user(user).build());

    vote.setType(request.getType());

    voteRepositoty.save(vote);

    addOrUpdateVote(voteable, vote);
  }

  private Voteable getVoteable(final VoteRequest request) {
    if (VoteEntity.QUESTION.equals(request.getEntity())) {
      return questionService.getQuestionById(request.getId());
    }
    if (VoteEntity.ANSWER.equals(request.getEntity())) {
      return answerService.getAnswerById(request.getId());
    }
    if (VoteEntity.COMMENT.equals(request.getEntity())) {
      return commentService.getCommentById(request.getId());
    }

    throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Voteable entity not implemented");
  }

  private void addOrUpdateVote(final Voteable voteable, final Vote vote) {
    if (voteable instanceof Question) {
      questionService.addOrUpdateVote((Question) voteable, vote);
    } else if (voteable instanceof Comment) {
      commentService.addOrUpdateVote((Comment) voteable, vote);
    } else {
      answerService.addOrUpdateVote((Answer) voteable, vote);
    }
  }
}
