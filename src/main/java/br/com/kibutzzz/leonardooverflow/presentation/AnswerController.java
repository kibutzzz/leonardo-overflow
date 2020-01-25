package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.AnswerService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.mapper.AnswerMapper;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.AnswerRequest;
import br.com.kibutzzz.leonardooverflow.presentation.resources.response.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    private final AnswerMapper answerMapper;

    @PostMapping("/{questionId}")
    public AnswerResponse answerQuestion(@PathVariable Long questionId, @RequestBody @Valid AnswerRequest request,
                                         @AuthenticationPrincipal User user) {

        return answerMapper.toResponse(answerService.answerQuestion(questionId, request, user));
    }

}
