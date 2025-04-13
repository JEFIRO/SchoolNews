package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.*;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.service.MemberService;
import com.SchoolNews.jefiro.br.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/v1")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping(value = "/singup")
    public ResponseEntity<?> register(@RequestBody @Valid UpMembersDTO data) throws Exception {

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        if (data.schoolID() == null || data.schoolID().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("school not found");
        }

        if (repository.existsByEmail(data.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");
        }
        var response = memberService.newMember(data);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/singin")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthDTO data) {
        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        var userNamePassWord = new UsernamePasswordAuthenticationToken(data.email(), data.passWord());

        var auth = this.authenticationManager.authenticate(userNamePassWord);


        var token = tokenService.generateToken((MembersModel) auth.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponse(new TokenDTO(token), new MemberDTO(repository.findByEmail(data.email()))));
    }

    @GetMapping("/find/{date}")
    public ResponseEntity<?> findMember(@PathVariable String date) {
        try {
            var response = memberService.findMember(date);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/allMember/{schoolId}")
    public ResponseEntity<List<MemberDTO>> findAllMember(@PathVariable String schoolId) {
        try {
            System.out.printf(schoolId);
            var response = memberService.findAllMember(schoolId);
            System.out.println(response);
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
