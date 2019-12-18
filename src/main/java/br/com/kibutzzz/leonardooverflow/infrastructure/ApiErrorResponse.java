package br.com.kibutzzz.leonardooverflow.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private String error;

    private String field;


    public ApiErrorResponse(String error) {
        this.error = error;
    }

}
