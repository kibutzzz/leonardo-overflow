package br.com.kibutzzz.leonardooverflow.application;

import br.com.kibutzzz.leonardooverflow.infrastructure.ApiException;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.QuestionRepository;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Answer;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Comment;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Question;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.Vote;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.QuestionMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.UpdateQuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository questionRepository;

  private final UserService userService;

  private final TagService tagService;

  private final QuestionMapper questionMapper;

  public List<Question> listQuestions() {
    return questionRepository.findAll();
  }

  @Transactional
  public Question createQuestion(final CreateQuestionRequest questionRequest, final User user) {
    final Question question = questionMapper.fromRequest(questionRequest);
    question.setUser(user);

    question.setTags(tagService.findAllTagsByIds(questionRequest.getTagsIds()));
    question.setCreationDate(LocalDateTime.now());

    return questionRepository.save(question);
  }

  public Question getQuestionById(final Long id) {
    return questionRepository.findById(id).orElseThrow(
      () -> new ApiException(HttpStatus.NOT_FOUND, "question not found"));
  }

  public List<Question> searchQuestionsByExpression(final String expression) {
    return questionRepository.findByTitleContainingIgnoreCase(expression);
  }

  public Question updateQuestion(final UpdateQuestionRequest questionRequest, final Long questionId, final User user) {
    final Question question = getQuestionById(questionId);

    if (!user.equals(question.getUser())) {
      throw new AccessDeniedException("Only the question owner can update the question");
    }

    question.setTitle(questionRequest.getTitle());
    question.setDescription(questionRequest.getDescription());

    question.setUpdateDate(LocalDateTime.now());

    return questionRepository.save(question);
  }

  public List<Question> listQuestionsByUserId(final Long id) {
    if (!userService.userExistsById(id)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "User not found");
    }

    return questionRepository.findByUserId(id);
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void addComment(final Question question, final Comment savedComment) {

    question.getComments().add(savedComment);

    questionRepository.save(question);
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void addOrUpdateVote(final Question question, final Vote vote) {

    final List<Vote> votes = question.getVotes().stream()
      .filter(v -> !v.getId().equals(vote.getId())).collect(Collectors.toList());

    votes.add(vote);
    question.setVotes(votes);

    questionRepository.save(question);
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void addAnswer(final Long questionId, final Answer savedAnswer) {

    final Question question = getQuestionById(questionId);

    question.getAnswers().add(savedAnswer);

    questionRepository.save(question);
  }

  public List<Question> searchQuestionsByTag(final String tag) {
    return questionRepository.findByTagsName(tag);
  }

}
