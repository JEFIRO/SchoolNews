package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.MemberDTO;
import com.SchoolNews.jefiro.br.models.dto.UpMembersDTO;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    public UpMembersDTO nMember(UpMembersDTO date, String passWordEncry) {
        if (date == null) {return null;}

        MembersModel model = new MembersModel(date, passWordEncry);

        memberRepository.save(model);
        return date;
    }

    public MemberDTO findMember(String date) {
        MembersModel member = memberRepository.findById(date).get();

        return new MemberDTO(member);
    }

    public List<MemberDTO> findAllMember() {
        return memberRepository.findAll().stream()
                .map(date -> new MemberDTO(date.get_id(), date.getName(),
                        date.getRole(), date.getImage(), date.getDateCreated(), date.getDateUpdated(),
                        date.getStatus(), date.getPublishedPermission(),date.getAccountNotLocked()))
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String date) throws UsernameNotFoundException {
        return memberRepository.findByEmail(date);
    }
}
