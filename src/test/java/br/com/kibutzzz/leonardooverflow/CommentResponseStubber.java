package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.presentation.resources.response.CommentResponse;

import java.util.ArrayList;
import java.util.List;

public class CommentResponseStubber {
    public static List<CommentResponse> generateCompleteCommentResponseList() {
        List<CommentResponse> comments = new ArrayList<>();
        comments.add(generateCompleteComment());

        return comments;
    }

    public static CommentResponse generateCompleteComment() {
        return CommentResponse.builder()
                .id(1L)
                .text("COMMENT ONE")
                .user(SimplifiedUserResponseStubber.generateCompleteResponse())
                .voteCount(1)
                .build();
    }

}
