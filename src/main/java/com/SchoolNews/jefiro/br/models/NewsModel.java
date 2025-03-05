package com.SchoolNews.jefiro.br.models;


import com.SchoolNews.jefiro.br.mebers.MembersModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MembersModel Members;
}

