package com.SchoolNews.jefiro.br.models.dto;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.enumm.MemberRole;

public record MemberDTO(
        String _id,
        String name,
        MemberRole role,
        String image,
        String dateCreated ,
        String dateUpdated,
        Boolean status,
        Boolean publishedPermission,
        Boolean accountNotLocked
) {
    public MemberDTO(MembersModel date) {
        this(
                date.get_id(),
                date.getName(),
                date.getRole(),
                date.getImage(),
                date.getDateCreated(),
                date.getDateUpdated(),
                date.getStatus(),
                date.getPublishedPermission(),
                date.getAccountNotLocked()
        );
    }
}
