package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private MemberRepository memberRepository;

    public String chengeUserStatus(String id) {
        var member = memberRepository.findById(id);
        if (member.isPresent()){
            var memberGet = member.get();
            memberGet.setStatus(!memberGet.getStatus());
            memberRepository.save(memberGet);
        }else return "User not found";

        return "User status changed successfully";
    }

    public void deleteUser(String id) {}

}
