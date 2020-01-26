package br.com.kibutzzz.leonardooverflow.presentation.resources.mapper;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.VoteType;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {VoteMapperImpl.class})
class VoteMapperTest {

    private static final List<Vote> NULL_VOTE_LIST = null;
    private static final List<Vote> EMPTY_VOTE_LIST = new ArrayList<>();

    @Autowired
    private VoteMapper voteMapper;

    @Test
    public void test_countVoteTotals_withNullList_shouldReturnZero() {

        assertEquals(0, voteMapper.countVoteTotals(NULL_VOTE_LIST));
    }

    @Test
    public void test_countVoteTotals_withEmptyList_shouldReturnZero() {

        assertEquals(0, voteMapper.countVoteTotals(EMPTY_VOTE_LIST));
    }

    @Test
    public void test_countVoteTotals_withMixedVotedList_shouldReturnCorrectVoteCount() {

        List<Vote> voteList = new ArrayList<>();

        voteList.add(Vote.builder().type(VoteType.UP).build());
        voteList.add(Vote.builder().type(VoteType.UP).build());
        voteList.add(Vote.builder().type(VoteType.DOWN).build());
        voteList.add(Vote.builder().type(VoteType.UP).build());
        voteList.add(Vote.builder().type(VoteType.UP).build());
        voteList.add(Vote.builder().type(VoteType.DOWN).build());
        voteList.add(Vote.builder().type(VoteType.UP).build());

        assertEquals(3, voteMapper.countVoteTotals(voteList));
    }

}