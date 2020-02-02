package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateCommentRequest {

  @NotBlank
  @Size(min = 3, max = 255)
  private String text;

  @NotNull
  private CommentEntity entity;

}
