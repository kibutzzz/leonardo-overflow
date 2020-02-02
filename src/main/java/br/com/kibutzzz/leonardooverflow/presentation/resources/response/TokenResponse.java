package br.com.kibutzzz.leonardooverflow.presentation.resources.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {

  private String token;

  private String type;
}
