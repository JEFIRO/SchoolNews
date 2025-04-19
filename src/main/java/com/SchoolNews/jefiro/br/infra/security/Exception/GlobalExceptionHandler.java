package com.SchoolNews.jefiro.br.infra.security.Exception;


import com.SchoolNews.jefiro.br.models.dto.ExceptionHandlerDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<ExceptionHandlerDTO> IdNotFound(NoSuchElementException content) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionHandlerDTO(HttpStatus.NOT_FOUND, content.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<ExceptionHandlerDTO> conflictMembers(DataIntegrityViolationException context) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionHandlerDTO(HttpStatus.CONFLICT, context.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<ExceptionHandlerDTO> badCredential(BadCredentialsException context) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionHandlerDTO(HttpStatus.UNAUTHORIZED, context.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleUnauthorized(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionHandlerDTO(HttpStatus.UNAUTHORIZED, e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleUnauthorized(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionHandlerDTO(HttpStatus.NOT_FOUND, e.getMessage()));
    }

}
