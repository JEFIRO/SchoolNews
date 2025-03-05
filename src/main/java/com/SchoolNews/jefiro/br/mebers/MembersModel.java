package com.SchoolNews.jefiro.br.mebers;

import com.SchoolNews.jefiro.br.models.NewsModel;
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

@Table(name = "members")
@Entity
public class MembersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private String image;
    private String imageDescription;
    private String dateCreated;
    private String dateUpdated;
    private Boolean status;
    private Boolean publishedPermission;

    @OneToMany(mappedBy = "Members",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsModel> news;

}
