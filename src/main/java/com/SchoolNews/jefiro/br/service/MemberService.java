package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.models.MembersModel;
import com.SchoolNews.jefiro.br.models.dto.MembersDTO;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MembersDTO neNews(MembersDTO data) {
        if (data == null) {return null;}

        MembersModel model = new MembersModel(data);
        System.out.printf(model.toString());
        memberRepository.save(model);
        return data;
    }
}
