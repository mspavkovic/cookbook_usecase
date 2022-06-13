package com.valcon.cookbook.exception;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RecipeExceptionHandler {

    private final HttpServletRequest request;

    public RecipeExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomError> exception(final EntityNotFoundException e, final HttpServletRequest httpServletRequest) {
        log.info("Entity not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new CustomError(
                                     "Entity not found",
                                     e.getMessage()));
    }

}
