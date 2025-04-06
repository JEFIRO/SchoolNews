package com.SchoolNews.jefiro.br.infra.security;


import com.SchoolNews.jefiro.br.models.dto.ExceptionHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<ExceptionHandlerDTO> IdNotFound(NoSuchElementException content) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionHandlerDTO(HttpStatus.NOT_FOUND, content.getMessage()));
    }


}
