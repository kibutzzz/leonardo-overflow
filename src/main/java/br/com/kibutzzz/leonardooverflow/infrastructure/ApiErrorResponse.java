package br.com.kibutzzz.leonardooverflow.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

  private String error;

  private String field;


  public ApiErrorResponse(final String error) {
    this.error = error;
  }

}
