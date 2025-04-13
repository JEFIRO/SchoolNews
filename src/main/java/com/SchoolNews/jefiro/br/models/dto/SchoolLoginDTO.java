package com.SchoolNews.jefiro.br.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SchoolLoginDTO(
        @NotNull
        @NotBlank
        String schoolName,
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        String passWord
) {
}
