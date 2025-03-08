package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.models.enumm.MemberRole;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private MemberRepository memberRepository;


    public String chengeUserStatus(String id) {
        var member = memberRepository.findById(id);
        if (member.isPresent()) {
            var memberGet = member.get();
            if (memberGet.getRole() == MemberRole.ADMIN) {
                return "You can't change an admin status";
            }
            memberGet.setStatus(!memberGet.getStatus());
            memberRepository.save(memberGet);
        } else return "User not found";

        return "User status changed successfully";
    }

    public String AccountLocked(String id) {
        var member = memberRepository.findById(id);

        if (member.isEmpty()) {
            return "User Not found";
        }

        var memberGet = member.get();

        if (memberGet.getRole() == MemberRole.ADMIN) {
            return "You can't block an admin account";
        }

        memberGet.setAccountNotLocked(!memberGet.getAccountNotLocked());
        memberRepository.save(memberGet);


        return "User blockd changed successfully";
    }

    public String changedPemission(String id, MemberRole role) {
        var member = memberRepository.findById(id);
        if (member.isEmpty()) {
            return "User Not found";
        }

        var memberGet = member.get();

        if (memberGet.getRole() == MemberRole.ADMIN) {
            return "You can't change an admin pemission";
        }

        memberGet.setRole(role);
        memberRepository.save(memberGet);

        return "User permission changed successfully";
    }

}
