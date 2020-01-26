package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.CommentRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.*;
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
import org.springframework.web.client.HttpClientErrorException;

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
    public Comment createComment(Long id, CreateCommentRequest commentRequest, @NonNull User user) {

        Commentable commentable = getCommentable(commentRequest.getEntity(), id);

        Comment comment = commentMapper.fromRequest(commentRequest);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        addComment(commentable, savedComment);
        return savedComment;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "comment not found"));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addOrUpdateVote(Comment comment, Vote vote) {
        List<Vote> votes = comment.getVotes().stream()
                .filter(v -> !v.getId().equals(vote.getId())).collect(Collectors.toList());

        votes.add(vote);
        comment.setVotes(votes);

        commentRepository.save(comment);
    }

    private Commentable getCommentable(CommentEntity entity, Long id) {

        if (CommentEntity.ANSWER.equals(entity)) {
            return answerService.getAnswerById(id);
        }

        if (CommentEntity.QUESTION.equals(entity)) {
            return questionService.getQuestionById(id);
        }

        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Commentable Entity not implemented");
    }

    private void addComment(Commentable commentable, Comment comment) {
        if (commentable instanceof Answer) {
            answerService.addComment((Answer) commentable, comment);
        } else if (commentable instanceof Question) {
            questionService.addComment((Question) commentable, comment);
        }
    }

    @Transactional
    public void deleteComment(Long entityId, DeleteCommentRequest deleteRequest, User user) {
        Commentable commentable = getCommentable(deleteRequest.getEntity(), entityId);

        Comment comment = getCommentById(deleteRequest.getCommentId());
        if(!comment.getUser().equals(user)) {
            throw new AccessDeniedException("You are only able to delete your own comments");
        }

        commentable.getComments().remove(comment);

        commentRepository.delete(comment);
    }
}
