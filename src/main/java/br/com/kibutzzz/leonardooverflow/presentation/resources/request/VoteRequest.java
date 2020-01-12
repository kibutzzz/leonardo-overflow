package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import br.com.kibutzzz.leonardooverflow.infrastructure.persistence.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequest {

    @NotNull
    private VoteType type;

    @NotNull
    @Positive
    private Long id;

    @NotNull
    private VoteEntity entity;

}
