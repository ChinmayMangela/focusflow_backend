package com.example.chinmay.focusflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage()
                        ));

        return new ResponseEntity<>(
                new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation failed",
                        errors
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleUnreadableRequestBody(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Invalid request body. Please check the provided values.",
                        Map.of()
                ),
                HttpStatus.BAD_REQUEST

        );
    }
}
