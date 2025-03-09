package com.SchoolNews.jefiro.br.controller;

import com.SchoolNews.jefiro.br.models.dto.PermissionDTO;
import com.SchoolNews.jefiro.br.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {
    @Autowired
    private PermissionService service;

    @PostMapping("blockacount")
    public ResponseEntity<?> changeAccountLocked(@RequestBody @Valid PermissionDTO permissionDTO) {
        return service.accountLocked(permissionDTO._id());
    }
}
