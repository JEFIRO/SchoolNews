package com.SchoolNews.jefiro.br.models.dto;

public record HomeDTO(
        String member_id,
        String _id,
        String title,
        String description,
        String imageMain,
        String imageMainDescription,
        String datePublished,
        String author,
        String lead,
        String content,
        String dateUpdated,
        Integer like) {}
