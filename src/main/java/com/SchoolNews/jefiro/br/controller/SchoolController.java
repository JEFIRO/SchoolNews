package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.dto.MemberResponseDTO;
import com.SchoolNews.jefiro.br.models.dto.SchoolLoginDTO;
import com.SchoolNews.jefiro.br.models.dto.loginSchoolResponse;
import com.SchoolNews.jefiro.br.service.SchoolModelDTO;
import com.SchoolNews.jefiro.br.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class SchoolController {
    @Autowired
    private SchoolService service;

    @PostMapping("/newSchool")
    public ResponseEntity<MemberResponseDTO> saveSchool(@RequestBody SchoolModelDTO date) {
        return ResponseEntity.ok(service.newSchool(date));
    }

    @PostMapping("/loginSchool")
    public ResponseEntity<loginSchoolResponse> loginSchool(@RequestBody @Valid SchoolLoginDTO date) {
        return ResponseEntity.ok(service.loginSchool(date));
    }

    @GetMapping("/_id/{id}")
    public ResponseEntity<MemberResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(new MemberResponseDTO(service.findByID(id)));
    }

}
