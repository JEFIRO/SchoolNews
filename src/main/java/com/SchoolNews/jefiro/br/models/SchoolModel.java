package com.SchoolNews.jefiro.br.models;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.MemberResponseDTO;
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

    @OneToMany(mappedBy = "schoolModel")
    private List<MembersModel> members;


    public SchoolModel(SchoolModelDTO date) {
        schoolName = date.schoolName();
        schoolPhone = date.schoolPhone();
        schoolEndereco = date.schoolEndereco();

    }

    public SchoolModel(MemberResponseDTO date) {
        this.schoolName = date.schoolName();
        this.schoolPhone = date.schoolPhone();
        this.schoolEndereco = date.schoolEndereco();
        this.members  = date.membersModelList();
    }
}
