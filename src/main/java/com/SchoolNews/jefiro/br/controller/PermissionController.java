package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.dto.MemberDTO;
import com.SchoolNews.jefiro.br.models.dto.PermissionDTO;
import com.SchoolNews.jefiro.br.models.enumm.MemberRole;
import com.SchoolNews.jefiro.br.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {
    @Autowired
    private PermissionService service;

    @GetMapping("/role/{id}/{role}")
    public ResponseEntity<MemberDTO> changeRole(@PathVariable String id, @PathVariable String role) {
        return ResponseEntity.ok().body(service.changedPemission(id, role));
    }

    @GetMapping("/blockAccount/{id}")
    public ResponseEntity<MemberDTO> blockAccount(@PathVariable String id){
        return ResponseEntity.ok().body(service.accountLocked(id));
    }


}
