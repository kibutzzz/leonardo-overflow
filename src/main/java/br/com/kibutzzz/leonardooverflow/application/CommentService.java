package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.CommentRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Commentable;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.CommentMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CommentEntity;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateCommentRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.DeleteCommentRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final CommentMapper commentMapper;

  private final QuestionService questionService;

  private final AnswerService answerService;

  @Transactional
  public Comment createComment(final Long id, final CreateCommentRequest commentRequest, @NonNull final User user) {

    final Commentable commentable = getCommentable(commentRequest.getEntity(), id);

    final Comment comment = commentMapper.fromRequest(commentRequest);
    comment.setUser(user);

    final Comment savedComment = commentRepository.save(comment);

    addComment(commentable, savedComment);
    return savedComment;
  }

  public Comment getCommentById(final Long id) {
    return commentRepository.findById(id).orElseThrow(
      () -> new ApiException(HttpStatus.NOT_FOUND, "comment not found"));
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void addOrUpdateVote(final Comment comment, final Vote vote) {
    final List<Vote> votes = comment.getVotes().stream()
      .filter(v -> !v.getId().equals(vote.getId())).collect(Collectors.toList());

    votes.add(vote);
    comment.setVotes(votes);

    commentRepository.save(comment);
  }

  private Commentable getCommentable(final CommentEntity entity, final Long id) {

    if (CommentEntity.ANSWER.equals(entity)) {
      return answerService.getAnswerById(id);
    }

    if (CommentEntity.QUESTION.equals(entity)) {
      return questionService.getQuestionById(id);
    }

    throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Commentable Entity not implemented");
  }

  private void addComment(final Commentable commentable, final Comment comment) {
    if (commentable instanceof Answer) {
      answerService.addComment((Answer) commentable, comment);
    } else if (commentable instanceof Question) {
      questionService.addComment((Question) commentable, comment);
    }
  }

  @Transactional
  public void deleteComment(final Long entityId, final DeleteCommentRequest deleteRequest, final User user) {

    final Comment comment = getCommentById(deleteRequest.getCommentId());
    if (!comment.getUser().equals(user)) {
      throw new AccessDeniedException("You are only able to delete your own comments");
    }

    final Commentable commentable = getCommentable(deleteRequest.getEntity(), entityId);
    commentable.getComments().remove(comment);

    commentRepository.delete(comment);
  }
}
