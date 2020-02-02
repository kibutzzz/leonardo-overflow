package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateTagRequest {

  @NotBlank
  @Size(min = 2, max = 32)
  private String name;

  @NotBlank
  @Size(min = 5, max = 512)
  private String description;

}
