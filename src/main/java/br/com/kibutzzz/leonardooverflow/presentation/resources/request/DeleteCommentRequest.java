package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentRequest {

    @NotNull
    private Long commentId;

    @NotNull
    private CommentEntity entity;

}
