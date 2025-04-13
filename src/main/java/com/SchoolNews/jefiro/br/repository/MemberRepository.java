package com.SchoolNews.jefiro.br.repository;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<MembersModel, String> {
    MembersModel findByEmail(String email);
    MembersModel findByEmailAndSchoolModel_SchoolName(String email,String school);
    Boolean existsByEmail(String email);
    @Query("SELECT m FROM MembersModel m WHERE m.schoolModel._id = :schoolId")
    List<MembersModel> findAllMembres(String schoolId);
}
