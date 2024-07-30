package com.kalekt.school.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Generic method for creating ResponseEntity with ErrorDetails
    private ResponseEntity<ErrorDetails> createErrorResponse(final Exception exception, final WebRequest webRequest, final HttpStatus status) {
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        log.error("Exception: {}, Status: {}", exception.getMessage(), status, exception);
        return new ResponseEntity<>(errorDetails, status);
    }

    // Specific error handlers
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleRecordNotFoundException(final RecordNotFoundException exception, final WebRequest webRequest) {
        return createErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorDetails> handleValidationException(final ValidationException exception, final WebRequest webRequest) {
        return createErrorResponse(exception, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<ErrorDetails> handleInvalidRequestException(final InvalidRequestException exception, final WebRequest webRequest) {
        return createErrorResponse(exception, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetails> handleIllegalArgumentException(final IllegalArgumentException exception, final WebRequest webRequest) {
        return createErrorResponse(exception, webRequest, HttpStatus.NOT_ACCEPTABLE);
    }

    // Global exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(final Exception exception, final WebRequest webRequest) {
        return createErrorResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers,
                                                                  final HttpStatus status, final WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Validation failed: {}", errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

