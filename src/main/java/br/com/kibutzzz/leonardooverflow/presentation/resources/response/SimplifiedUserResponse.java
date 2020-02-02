package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimplifiedUserResponse {

  private Long id;

  private String username;

}
