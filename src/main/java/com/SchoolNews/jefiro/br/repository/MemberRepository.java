package com.SchoolNews.jefiro.br.repository;

import com.SchoolNews.jefiro.br.models.MembersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MembersModel, String> {

}
