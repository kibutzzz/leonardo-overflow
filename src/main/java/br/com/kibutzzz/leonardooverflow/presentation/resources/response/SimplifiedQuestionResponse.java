package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import lombok.Data;

import java.util.List;

@Data
public class SimplifiedQuestionResponse {

  private Long id;

  private String title;

  private String description;

  private Long voteCount;

  private SimplifiedUserResponse user;

  private List<SimplifiedTagResponse> tags;
}
