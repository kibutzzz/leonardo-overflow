package br.com.kibutzzz.leonardooverflow;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.VoteType;

import java.util.ArrayList;
import java.util.List;

public class VoteStubber {

    public static List<Vote> generatePositiveVoteList() {
        List<Vote> votes = new ArrayList<>();

        votes.add(Vote.builder().type(VoteType.UP).build());
        votes.add(Vote.builder().type(VoteType.UP).build());
        votes.add(Vote.builder().type(VoteType.DOWN).build());

        return votes;
    }

    public static List<Vote> generateNegativeVoteList() {
        List<Vote> votes = new ArrayList<>();

        votes.add(Vote.builder().type(VoteType.DOWN).build());
        votes.add(Vote.builder().type(VoteType.UP).build());
        votes.add(Vote.builder().type(VoteType.DOWN).build());

        return votes;
    }
}
