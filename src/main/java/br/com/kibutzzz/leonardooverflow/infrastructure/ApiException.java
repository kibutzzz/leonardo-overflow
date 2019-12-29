package br.com.kibutzzz.leonardooverflow.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private HttpStatus status;

    private String message;


    public ResponseEntity<ApiErrorResponse> toResponseEntity() {
        ApiErrorResponse errorResponse = new ApiErrorResponse(message);
        return ResponseEntity.status(status).body(errorResponse);
    }

}
