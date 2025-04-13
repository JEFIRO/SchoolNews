package com.SchoolNews.jefiro.br.models.dto;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.SchoolModel;

import java.util.List;

public record MemberResponseDTO(
        String _id,
        String schoolName,
        String schoolPhone,
        String schoolEndereco,
        List<MembersModel> membersModelList,
        TokenDTO token
) {
    public MemberResponseDTO(SchoolModel date) {
        this(date.get_id(), date.getSchoolName(), date.getSchoolPhone(), date.getSchoolEndereco(), date.getMembers(),null);
    }


}
