package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.dto.HomeDTO;
import com.SchoolNews.jefiro.br.models.dto.NewsModelDTO;
import com.SchoolNews.jefiro.br.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    @PostMapping("new")
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
}
