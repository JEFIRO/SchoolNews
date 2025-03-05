package com.SchoolNews.jefiro.br.models;

public record NewsModelDTO(
        String title,
        String description,
        String imageMain,
        String imageMainDescription,
        String datePublished,
        String author,
        String lead,
        String content
) {}
