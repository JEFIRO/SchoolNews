package com.SchoolNews.jefiro.br.repository;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MembersModel, String> {
    MembersModel findByEmail(String email);
    Boolean existsByEmail(String email);
}
