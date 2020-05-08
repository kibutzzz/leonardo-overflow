package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.QuestionService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.QuestionMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.UpdateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.QuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedQuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SpecificQuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.validator.CreateQuestionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

  private final QuestionService questionService;

  private final CreateQuestionValidator createQuestionValidator;

  private final QuestionMapper questionMapper;

  @InitBinder("createQuestionRequest")
  public void setCreateQuestionValidator(final WebDataBinder binder) {
    binder.addValidators(createQuestionValidator);
  }

  @GetMapping
  public List<SimplifiedQuestionResponse> listQuestions() {

    return questionMapper.toSimplifiedQuestionResponse(questionService.listQuestions());
  }

  @GetMapping("/{id}")
  public SpecificQuestionResponse getQuestionById(@PathVariable final Long id) {
    return questionMapper.toSpecificQuestionResponse(questionService.getQuestionById(id));
  }

  @GetMapping("/user/{id}")
  public List<SimplifiedQuestionResponse> getByUserId(@PathVariable final Long id) {
    return questionMapper.toSimplifiedQuestionResponse(questionService.listQuestionsByUserId(id));
  }

  @PostMapping
  public QuestionResponse createQuestion(@Valid @RequestBody final CreateQuestionRequest questionRequest,
                                         @AuthenticationPrincipal final User user) {
    return questionMapper.toResponse(questionService.createQuestion(questionRequest, user));
  }

  @GetMapping("/search/{expression}")
  public List<SimplifiedQuestionResponse> searchQuestionsByExpression(@PathVariable final String expression) {

    return questionMapper.toSimplifiedQuestionResponse(questionService.searchQuestionsByExpression(expression));
  }

  @PatchMapping("/{questionId}")
  public QuestionResponse updateQuestion(@Valid @RequestBody final UpdateQuestionRequest questionRequest,
                                         @PathVariable final Long questionId,
                                         @AuthenticationPrincipal final User user) {
    return questionMapper.toResponse(questionService.updateQuestion(questionRequest, questionId, user));
  }

  @GetMapping("/tag/{tag}")
  public List<SimplifiedQuestionResponse> searchQuestionsByTags(@PathVariable final String tag) {
    return questionMapper.toSimplifiedQuestionResponse(questionService.searchQuestionsByTag(tag));
  }
}
