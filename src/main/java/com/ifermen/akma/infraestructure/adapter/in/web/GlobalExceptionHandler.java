package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.exception.ConfilctException;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //409 - CONFLICT
    @ExceptionHandler(ConfilctException.class)
    public ResponseEntity<ApiError> handleConfictException(ConfilctException ex, HttpServletRequest http){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("CONFLICT")
                .message(ex.getMessage())
                .path(http.getRequestURI())
                .method(http.getMethod())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(apiError);
    }
}
