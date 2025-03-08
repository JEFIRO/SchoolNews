package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.AuthDTO;
import com.SchoolNews.jefiro.br.models.dto.FindMemberDTO;
import com.SchoolNews.jefiro.br.models.dto.MembersDTO;
import com.SchoolNews.jefiro.br.models.dto.TokenDTO;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.service.MemberService;
import com.SchoolNews.jefiro.br.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("auth/v1/singup")
    public ResponseEntity<?> register(@RequestBody @Valid MembersDTO data) {
        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        // Verificar se o email já está registrado
        if (repository.existsByEmail(data.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está em uso.");
        }

        String encryptedPassWord = new BCryptPasswordEncoder().encode(data.passWord());
        var response = memberService.nMember(data, encryptedPassWord);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("auth/v1/singin")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO data) {
        if (data == null) {return ResponseEntity.badRequest().build();}
        var userNamePassWord = new UsernamePasswordAuthenticationToken(data.email(), data.passWord());
        var auth = this.authenticationManager.authenticate(userNamePassWord);

        var token = tokenService.generateToken((MembersModel) auth.getPrincipal());

        return ResponseEntity.ok().body(new TokenDTO(token));

    }

    @PostMapping("/find")
    public ResponseEntity<?> findMember(@RequestBody @Valid FindMemberDTO date){
        if (date == null || date.email() == null){return ResponseEntity.badRequest().build();}

        var response = memberService.findMember(date);

        return ResponseEntity.ok().body(response);
    }
}
