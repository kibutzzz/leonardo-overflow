package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateQuestionRequest {

    @NotBlank
    @Size(min = 5, max = 255)
    private String title;

    @NotBlank
    @Size(min = 32, max = 4096)
    private String description;

}
