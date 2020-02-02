package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.presentation.resources.response.AnswerResponse;

public class AnswerResponseStubber {
  public static AnswerResponse generateCompleteResponse() {
    return AnswerResponse.builder()
      .id(1L)
      .description("DESCRIPTION")
      .comments(CommentResponseStubber.generateCompleteCommentResponseList())
      .user(SimplifiedUserResponseStubber.generateCompleteResponse())
      .voteCount(-1)
      .build();
  }
}
