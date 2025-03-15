package com.SchoolNews.jefiro.br.models;


import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.HomeDTO;
import com.SchoolNews.jefiro.br.models.dto.NewsModelDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "news")
@Entity
public class NewsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;
    private String title;
    private String description;
    private String imageMain;
    private String imageMainDescription;
    private String datePublished;
    private String author;
    private String lead;
    @Column(length = 100000)
    private String content;
    private String dateUpdated;
    private Boolean status;
    private Boolean published;
    private Boolean lockedNews;


    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MembersModel members;

    public NewsModel(NewsModelDTO date, MembersModel membersModel){
        this.members = membersModel;
        this.title = date.title();
        this.description = date.description();
        this.imageMain = date.imageMain();
        this.imageMainDescription = date.imageMainDescription();
        this.datePublished = LocalDateTime.now().toString();
        this.author = date.author();
        this.lead = date.lead();
        this.content = date.content();
        this.status = true;
        this.lockedNews = false;
        this.published = true;
        this.dateUpdated = LocalDateTime.now().toString();
    }
    public NewsModel(HomeDTO date){
        this.title = date.title();
        this.description = date.description();
        this.imageMain = date.imageMain();
        this.imageMainDescription = date.imageMainDescription();
        this.datePublished = LocalDateTime.now().toString();
        this.author = date.author();
        this.lead = date.lead();
        this.content = date.content();
    }
}
