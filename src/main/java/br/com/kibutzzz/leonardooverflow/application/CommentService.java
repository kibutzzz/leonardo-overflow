package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.CommentRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.CommentMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateCommentRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final QuestionService questionService;

    @Transactional
    public Comment createComment(Long questionId, CreateCommentRequest commentRequest, @NonNull User user) {

        Comment comment = commentMapper.fromRequest(commentRequest);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        questionService.addComment(questionId, savedComment);

        return savedComment;
    }

}
