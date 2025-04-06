package com.SchoolNews.jefiro.br.repository;

import com.SchoolNews.jefiro.br.models.SchoolModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolModel, String> {

}
