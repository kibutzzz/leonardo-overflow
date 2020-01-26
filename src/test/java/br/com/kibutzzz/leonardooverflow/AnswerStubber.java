package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;

public class AnswerStubber {

    public static Answer generateCompleteAnswer() {

        return Answer.builder()
                .id(1L)
                .comments(CommentStubber.generateCompleteCommentList())
                .description("DESCRIPTION")
                .user(UserStubber.generateCompleteUser())
                .votes(VoteStubber.generateNegativeVoteList())
                .build();
    }
}
