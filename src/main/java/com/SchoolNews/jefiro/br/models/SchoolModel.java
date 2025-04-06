package com.SchoolNews.jefiro.br.models;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.service.MemberService;
import com.SchoolNews.jefiro.br.service.SchoolModelDTO;
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

@Entity
@Table(name = "school")
public class SchoolModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;
    private String schoolName;
    private String schoolPhone;
    private String schoolEndereco;

    @OneToMany(mappedBy = "schoolModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembersModel> membersModelList;

    public SchoolModel(SchoolModelDTO date) {
        schoolName = date.schoolName();
        schoolPhone = date.schoolPhone();
        schoolEndereco = date.schoolEndereco();

    }
}
