package com.SchoolNews.jefiro.br.repository;

import com.SchoolNews.jefiro.br.models.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsModel, String> {
}
