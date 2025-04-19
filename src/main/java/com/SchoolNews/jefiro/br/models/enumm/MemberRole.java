package com.SchoolNews.jefiro.br.models.enumm;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("admin"),
    EDITORCHIEF("editorChief"),
    EDITOR("editor"),
    STUDENT("student");

    private String role;

    MemberRole(String role) {
        this.role = role;
    }
}
