package br.com.kibutzzz.leonardooverflow.presentation;

import br.com.kibutzzz.leonardooverflow.application.VoteService;
import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.User;
import br.com.kibutzzz.leonardooverflow.presentation.resources.request.VoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> castVote(@Valid @RequestBody VoteRequest request, @AuthenticationPrincipal User user) {

        voteService.castVote(request, user.getId());

        return ResponseEntity.ok().build();
    }

}
