package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionFilter {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<BadRequestErrorDto>> handleBadRequestError(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(BadRequestErrorDto::new).toList());
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<String> handleDomainValidationException(DomainValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record BadRequestErrorDto(String field, String message) {
        public BadRequestErrorDto(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
