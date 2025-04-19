package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.infra.security.Exception.UnauthorizedException;
import com.SchoolNews.jefiro.br.infra.security.Exception.UserNotFoundException;
import com.SchoolNews.jefiro.br.models.dto.MemberDTO;
import com.SchoolNews.jefiro.br.models.enumm.MemberRole;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NewsRepository newsRepository;

    public ResponseEntity<String> chengeUserStatus(String id) {
        var member = memberRepository.findById(id);

        if (member.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var memberGet = member.get();
        if (memberGet.getRole() == MemberRole.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't change an admin status");
        }

        memberGet.setStatus(!memberGet.getStatus());
        memberRepository.save(memberGet);


        return ResponseEntity.ok().body("User status changed successfully");
    }

    public MemberDTO accountLocked(String id) {
        var member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new UserNotFoundException("");
        }

        var memberGet = member.get();

        if (memberGet.getRole().equals(MemberRole.ADMIN)) {
            throw new UnauthorizedException("You can't change an admin status");
        }

        memberGet.setAccountNotLocked(!memberGet.getAccountNotLocked());
        memberRepository.save(memberGet);


        return new MemberDTO(memberGet);
    }

    public MemberDTO changedPemission(String id, String role) {
        var member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new UserNotFoundException("");
        }

        var memberGet = member.get();

        if (memberGet.getRole().equals(MemberRole.ADMIN)) {
            throw new UnauthorizedException("You can't change an admin status");
        }

        MemberRole memberRole = MemberRole.valueOf(role.toUpperCase());
        memberGet.setRole(memberRole);
        var membe =  memberRepository.save(memberGet);



        return new MemberDTO(membe);
    }

    public ResponseEntity<String> newsLocked(String id) {
        var news = newsRepository.findById(id);

        if (news.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        news.get().setLockedNews(!news.get().getStatus());

        return ResponseEntity.ok().body("User news locked successfully");
    }

}
