package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.AnswerRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, "answer not found"));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addOrUpdateVote(Answer answer, Vote vote) {
        List<Vote> votes = answer.getVotes().stream()
                .filter(v -> !v.getId().equals(vote.getId())).collect(Collectors.toList());

        votes.add(vote);
        answer.setVotes(votes);

        answerRepository.save(answer);
    }
}
