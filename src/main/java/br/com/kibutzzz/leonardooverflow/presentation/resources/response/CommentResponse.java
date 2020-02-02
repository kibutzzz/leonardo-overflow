package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

  private Long id;

  private String text;

  private Integer voteCount;

  private SimplifiedUserResponse user;
}
