package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.AnswerRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.AnswerMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.AnswerRequest;
import lombok.NonNull;
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

  private final AnswerMapper answerMapper;

  private final QuestionService questionService;

  public Answer getAnswerById(final Long id) {
    return answerRepository.findById(id).orElseThrow(
      () -> new ApiException(HttpStatus.NOT_FOUND, "answer not found"));
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void addOrUpdateVote(final Answer answer, final Vote vote) {
    final List<Vote> votes = answer.getVotes().stream()
      .filter(v -> !v.getId().equals(vote.getId())).collect(Collectors.toList());

    votes.add(vote);
    answer.setVotes(votes);

    answerRepository.save(answer);
  }

  @Transactional
  public Answer answerQuestion(final Long questionId, final AnswerRequest request, @NonNull final User user) {

    final Answer answer = answerRepository.save(answerMapper.fromRequest(request));
    answer.setUser(user);

    questionService.addAnswer(questionId, answer);

    return answer;
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void addComment(final Answer answer, final Comment saveComment) {
    answer.getComments().add(saveComment);

    answerRepository.save(answer);
  }
}
