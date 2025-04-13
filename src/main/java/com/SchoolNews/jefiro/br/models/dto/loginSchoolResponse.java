package com.SchoolNews.jefiro.br.models.dto;



public record loginSchoolResponse(
        MemberDTO member,
        String token,
        String schoolID
) {
}
