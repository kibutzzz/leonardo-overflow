package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.VoteType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class VoteRequest {

    @NotNull
    private VoteType type;

    @NotNull
    @Positive
    private Long questionId;

}
