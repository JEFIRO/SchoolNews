package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.models.dto.UpMembersDTO;

public record SchoolModelDTO(
        String _id,
        String schoolName,
        String schoolPhone,
        String schoolEndereco,
        UpMembersDTO memberMode) {
}
