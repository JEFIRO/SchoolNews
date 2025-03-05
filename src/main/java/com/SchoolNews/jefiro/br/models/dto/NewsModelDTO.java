package com.SchoolNews.jefiro.br.models.dto;

import com.SchoolNews.jefiro.br.models.MembersModel;

public record NewsModelDTO(
        String id,
        String title,
        String description,
        String imageMain,
        String imageMainDescription,
        String datePublished,
        String author,
        String lead,
        String content,
        MembersModel member
) {}
