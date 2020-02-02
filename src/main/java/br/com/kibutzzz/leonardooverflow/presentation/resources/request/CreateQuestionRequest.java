package br.com.kibutzzz.leonardooverflow.presentation.resources.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateQuestionRequest {

  @NotBlank
  @Size(min = 5, max = 255)
  private String title;

  @NotBlank
  @Size(min = 32, max = 4096)
  private String description;


  @Size(max = 6)
  private List<Long> tagsIds = new ArrayList<>();

}
