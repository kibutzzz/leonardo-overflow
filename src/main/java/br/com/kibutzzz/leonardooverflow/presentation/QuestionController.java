package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.QuestionService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.QuestionMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.CreateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.UpdateQuestionRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.QuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SimplifiedQuestionResponse;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.SpecificQuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<SimplifiedQuestionResponse> listQuestions() {

        return QuestionMapper.INSTANCE.toSimplifiedQuestionResponse(questionService.listQuestions());
    }

    @GetMapping("/{id}")
    public SpecificQuestionResponse getQuestionById(@PathVariable Long id) {
        return QuestionMapper.INSTANCE.toSpecificQuestionResponse(questionService.getQuestionById(id));
    }

    @GetMapping("/user/{id}")
    public List<SimplifiedQuestionResponse> getByUserId(@PathVariable Long id) {
        return QuestionMapper.INSTANCE.toSimplifiedQuestionResponse(questionService.listQuestionsByUserId(id));
    }

    @PostMapping
    public QuestionResponse createQuestion(@Valid @RequestBody CreateQuestionRequest questionRequest,
                                           @AuthenticationPrincipal User user) {
        return QuestionMapper.INSTANCE.toResponse(questionService.createQuestion(questionRequest, user));
    }

    @GetMapping("/search/{expression}")
    public List<SimplifiedQuestionResponse> searchQuestionsByExpression(@PathVariable String expression) {

        return QuestionMapper.INSTANCE.toSimplifiedQuestionResponse(
                questionService.searchQuestionsByExpression(expression));
    }

    @PatchMapping("/{questionId}")
    public QuestionResponse updateQuestion(@Valid @RequestBody UpdateQuestionRequest questionRequest,
                                           @PathVariable Long questionId, @AuthenticationPrincipal User user) {
        return QuestionMapper.INSTANCE.toResponse(questionService.updateQuestion(questionRequest, questionId, user));
    }
}
