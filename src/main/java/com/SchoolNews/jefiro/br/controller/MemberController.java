package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.dto.MembersDTO;
import com.SchoolNews.jefiro.br.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/new")
    public ResponseEntity<?> register(@RequestBody @Valid MembersDTO data) {
        if (data == null) {return ResponseEntity.badRequest().build();}

        var response = memberService.neNews(data);
        return ResponseEntity.ok().body(response);

    }
}
