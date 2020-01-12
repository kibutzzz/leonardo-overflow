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

    private final QuestionService questionService;

    @Transactional
    public void castVote(VoteRequest request, User user) {

        Question question = questionService.getQuestionById(request.getQuestionId());

        Vote vote = question.getVotes().stream()
                .filter(v -> user.equals(v.getUser()))
                .findFirst().orElse(Vote.builder().user(user).build());

        vote.setType(request.getType());

        voteRepositoty.save(vote);

        questionService.addOrUpdateVote(question, vote);

    }
}
