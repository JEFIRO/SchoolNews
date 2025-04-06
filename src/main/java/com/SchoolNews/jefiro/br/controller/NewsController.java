package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.NewsModel;
import com.SchoolNews.jefiro.br.models.dto.HomeDTO;
import com.SchoolNews.jefiro.br.models.dto.NewsModelDTO;
import com.SchoolNews.jefiro.br.repository.NewsRepository;
import com.SchoolNews.jefiro.br.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService service;

    @Autowired
    private NewsRepository newsRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    @PostMapping("/new")
    public ResponseEntity<?> NewNews(@RequestBody @Valid NewsModelDTO date) {
        if (date == null || date.member_id() == null) {
            return ResponseEntity.badRequest().build();
        }

        var response = service.nNews(date);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("home")
    public ResponseEntity<List<HomeDTO>> homePage() {
        List<HomeDTO> response = service.homePage();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("like/{memberId}/{newsID}")
    public ResponseEntity<String> likeNews(@PathVariable String memberId, @PathVariable String newsID) {
        if (memberId.isEmpty() || newsID.isEmpty()) {
            throw new NoSuchElementException("");
        }

        NewsModel news = newsRepository.findById(newsID).get();
        news.setLikes(news.getLikes() + 1);

        newsRepository.save(news);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
