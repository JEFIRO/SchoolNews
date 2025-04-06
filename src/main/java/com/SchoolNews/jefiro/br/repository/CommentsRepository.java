package com.SchoolNews.jefiro.br.repository;

import com.SchoolNews.jefiro.br.models.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comentarios, String> {
}
