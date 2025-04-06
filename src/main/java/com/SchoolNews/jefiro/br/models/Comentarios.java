package com.SchoolNews.jefiro.br.models;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String _id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MembersModel membersModels;


    @ManyToOne
    @JoinColumn(name = "news_id")
    private NewsModel news;


    Integer likes;
    private String content;
    private Boolean postComment;
    private Boolean isBlock;
    private Boolean isActive;

    public Comentarios(MembersModel membersModels, NewsModel newsModels, String content) {
        this.membersModels = membersModels;
        this.news = newsModels;
        this.content = content;
        this.postComment = true;
        this.isActive = true;
        this.isBlock = false;
    }
}
