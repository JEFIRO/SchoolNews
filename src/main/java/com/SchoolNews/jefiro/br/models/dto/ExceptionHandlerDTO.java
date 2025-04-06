package com.SchoolNews.jefiro.br.models.dto;

import org.springframework.http.HttpStatus;

public record ExceptionHandlerDTO(
        HttpStatus status,
        String content) {
}
