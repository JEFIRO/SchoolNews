package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.FindMemberDTO;
import com.SchoolNews.jefiro.br.models.dto.MembersDTO;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    public MembersDTO nMember(MembersDTO date, String passWordEncry) {
        if (date == null) {return null;}

        MembersModel model = new MembersModel(date, passWordEncry);

        memberRepository.save(model);
        return date;
    }
    public String findMember(FindMemberDTO date) {
        if (date == null) {
            return null;
        }
        var member = memberRepository.findByEmail(date.email());

        return member.get_id();
    }

    @Override
    public UserDetails loadUserByUsername(String date) throws UsernameNotFoundException {
        return memberRepository.findByEmail(date);
    }
}
