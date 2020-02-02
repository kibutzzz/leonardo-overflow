package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentStubber {

  public static List<Comment> generateCompleteCommentList() {
    final List<Comment> comments = new ArrayList<>();
    comments.add(generateCompleteComment());

    return comments;
  }

  public static Comment generateCompleteComment() {
    return Comment.builder()
      .id(1L)
      .text("COMMENT ONE")
      .user(UserStubber.generateCompleteUser())
      .votes(VoteStubber.generatePositiveVoteList())
      .build();
  }
}
