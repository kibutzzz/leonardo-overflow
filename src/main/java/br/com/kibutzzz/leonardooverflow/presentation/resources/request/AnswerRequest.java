package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {

    @NotNull
    @Size(min = 16, max = 4096)
    private String description;

}
