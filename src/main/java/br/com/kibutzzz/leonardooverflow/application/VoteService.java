package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.QuestionRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.UserRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.VoteRepositoty;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.VoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepositoty voteRepositoty;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    @Transactional
    public void castVote(VoteRequest request, Long userId) {

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Question Not Found"));


        User managedUser = userRepository.findUserById(userId);
        Vote vote = question.getVotes().stream()
                .filter(v -> managedUser.equals(v.getUser()))
                .findFirst().orElse(Vote.builder().user(managedUser).build());

        vote.setType(request.getType());

        voteRepositoty.save(vote);

        List<Vote> votes = question.getVotes().stream()
                .filter(v -> !v.getId().equals(vote.getId())).collect(Collectors.toList());

        votes.add(vote);
        question.setVotes(votes);

        questionRepository.save(question);

    }
}
