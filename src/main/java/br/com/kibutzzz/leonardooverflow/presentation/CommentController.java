package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.CommentService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.CommentMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateCommentRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentMapper commentMapper;

    private final CommentService commentService;


    @PostMapping("/{questionId}")
    public CommentResponse commentQuestion(@PathVariable Long questionId, @AuthenticationPrincipal User user,
                                           @RequestBody @Valid CreateCommentRequest commentRequest) {

        return commentMapper.toResponse(commentService.createComment(questionId, commentRequest, user));
    }


}
