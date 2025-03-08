package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.dto.NewsModelDTO;
import com.SchoolNews.jefiro.br.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService service;

    @PostMapping("new")
    public ResponseEntity<?> NewNews(@RequestBody @Valid NewsModelDTO date) {
        if (date == null || date.member_id() == null) {
            return ResponseEntity.badRequest().build();
        }

        var response = service.nNews(date);

        return ResponseEntity.ok().body(response);

    }

    @PostMapping("home")
    public ResponseEntity<?> homePage() {

        var response = service.homePage();

        return ResponseEntity.ok().body(response);
    }
}
