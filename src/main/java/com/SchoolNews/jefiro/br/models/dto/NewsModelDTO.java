package com.SchoolNews.jefiro.br.models.dto;


public record NewsModelDTO(
        String member_id,
        String title,
        String description,
        String imageMain,
        String imageMainDescription,
        String author,
        String lead,
        String content
) {
}
