package com.SchoolNews.jefiro.br.models.dto;

import com.SchoolNews.jefiro.br.domain.MembersModel;

public record UpMembersDTO(
        String schoolID,
        String name,
        String email,
        String passWord,
        String image
) {
    public UpMembersDTO(MembersModel member) {
        this(
            member.getSchoolModel().get_id(),
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getImage()
        );
    }
}