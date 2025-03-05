package com.SchoolNews.jefiro.br.models;

import com.SchoolNews.jefiro.br.models.dto.MembersDTO;
import com.SchoolNews.jefiro.br.models.enumm.MemberRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "members")
@Entity
public class MembersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private List<MemberRole> role = new ArrayList<>();

    private String image;
    private String dateCreated;
    private String dateUpdated;
    private Boolean status;
    private Boolean publishedPermission;

    @OneToMany(mappedBy = "Members",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsModel> news;

    public MembersModel(MembersDTO data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.passWord();
        this.role.add(MemberRole.STUDENT);
        this.image = data.image();
        this.dateCreated = LocalDateTime.now().toString();
        this.status = true;
        this.publishedPermission = true;

    }
}
