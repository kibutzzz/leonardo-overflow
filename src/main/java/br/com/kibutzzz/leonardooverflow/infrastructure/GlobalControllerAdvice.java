package br.com.kibutzzz.leonardooverflow.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        return ex.toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorResponse>> handleApiException(MethodArgumentNotValidException ex) {
        List<ApiErrorResponse> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
                    String message = messageSource.getMessage(error.getCode(), error.getArguments(),
                            error.getDefaultMessage(), LocaleContextHolder.getLocale());

                    errors.add(new ApiErrorResponse(message, error.getField()));
                }
        );

        return ResponseEntity.badRequest().body(errors);
    }

}
