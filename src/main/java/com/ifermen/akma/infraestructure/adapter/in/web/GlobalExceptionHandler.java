package com.ifermen.akma.infraestructure.adapter.in.web;

import com.ifermen.akma.application.exception.BadRequestException;
import com.ifermen.akma.application.exception.ConfilctException;
import com.ifermen.akma.application.exception.NotFoundException;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiError;
import com.ifermen.akma.infraestructure.adapter.in.web.dto.error.ApiErrorMessageList;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //400 - NOT VALID BODY
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessageList> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest http){
        ApiErrorMessageList apiError = ApiErrorMessageList.builder()
                .status(400)
                .error("NOT VALID BODY")
                .messages(
                        ex.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .toList()
                )
                .path(http.getRequestURI())
                .method(http.getMethod())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiError);
    }

    //400 - BAD REQUEST
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex, HttpServletRequest http){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("BAD REQUEST")
                .message(ex.getMessage())
                .path(http.getRequestURI())
                .method(http.getMethod())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiError);
    }

    //400 - TYPE MISMATCH
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest http) {
        ApiError apiError = ApiError.builder()
                .status(400)
                .error("TYPE MISMATCH")
                .message(String.format("'%s' must be a valid %s",
                        ex.getName(),
                        ex.getRequiredType() != null ?
                                ex.getRequiredType().getSimpleName() :
                                ex.getRequiredType().toString()))
                .path(http.getRequestURI())
                .method(http.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

    //404 - NOT FOUND
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, HttpServletRequest http){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error("NOT FOUND")
                .message(ex.getMessage())
                .path(http.getRequestURI())
                .method(http.getMethod())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(apiError);
    }

    //409 - CONFLICT
    @ExceptionHandler(ConfilctException.class)
    public ResponseEntity<ApiError> handleConfictException(ConfilctException ex, HttpServletRequest http){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .error("CONFLICT")
                .message(ex.getMessage())
                .path(http.getRequestURI())
                .method(http.getMethod())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(apiError);
    }
}
