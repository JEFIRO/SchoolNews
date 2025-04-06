package com.SchoolNews.jefiro.br.models.enumm;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("admin"),
    EDITOR("editor"),
    STUDENT("student"),
    EDITORCHIEF("editorChief");

    private String role;

    MemberRole(String role) {
        this.role = role;
    }
}
