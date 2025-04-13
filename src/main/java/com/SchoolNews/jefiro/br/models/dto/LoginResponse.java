package com.SchoolNews.jefiro.br.models.dto;

public record LoginResponse(TokenDTO token,
                            MemberDTO memberDTO) {
    }
