package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.SchoolModel;
import com.SchoolNews.jefiro.br.models.dto.MemberDTO;
import com.SchoolNews.jefiro.br.models.dto.UpMembersDTO;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SchoolRepository schoolRepository;



    public UpMembersDTO newMember(UpMembersDTO date) throws Exception {
        if (date == null) {
            return null;
        }

        String encryptedPassWord = new BCryptPasswordEncoder().encode(date.passWord());

        MembersModel model = new MembersModel(date);

        model.setPassword(encryptedPassWord);

        var school = schoolRepository.findById(date.schoolID()).get();

        model.setSchoolModel(school);


        var member = memberRepository.save(model);

        return date;
    }

    public MemberDTO findMember(String date) {
        MembersModel member = memberRepository.findById(date).get();

        return new MemberDTO(member);
    }

    public List<MemberDTO> findAllMember(String id) {
        return memberRepository.findAllMembres(id).stream().map(MemberDTO::new).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String date) throws UsernameNotFoundException {
        return memberRepository.findByEmail(date);
    }
}
