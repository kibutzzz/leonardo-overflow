package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.CommentService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.CommentMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateCommentRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.DeleteCommentRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  private final CommentMapper commentMapper;

  private final CommentService commentService;


  @PostMapping("/{id}")
  public CommentResponse commentQuestion(@PathVariable final Long id, @AuthenticationPrincipal final User user,
                                         @RequestBody @Valid final CreateCommentRequest commentRequest) {

    return commentMapper.toResponse(commentService.createComment(id, commentRequest, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable final Long id,
                                            @Valid @RequestBody final DeleteCommentRequest deleteRequest,
                                            @AuthenticationPrincipal final User user) {

    commentService.deleteComment(id, deleteRequest, user);
    return ResponseEntity.noContent().build();
  }


}
