package com.SchoolNews.jefiro.br.mebers;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("admin"),
    EDITOR("editor"),
    STUDENT("student");

    private String role;

    MemberRole(String role){
        this.role = role;
    }
}
