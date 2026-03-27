package com.loan.interfaces.rest;

import com.loan.application.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidation(ValidationException ex) {

        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", "ERROR",
                        "errors", ex.getErrors()
                )
        );
    }
}