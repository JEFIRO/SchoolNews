package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.SchoolModel;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void newSchool(SchoolModelDTO dto) {
        // Cria e salva a escola
        SchoolModel schoolModel = new SchoolModel(dto);
        schoolRepository.save(schoolModel);

        var memberDTO = dto.memberMode();

        if (memberDTO != null) {

            MembersModel membersModel = new MembersModel(); // Instancia "em branco"
            membersModel.memberModelADM(memberDTO, passwordEncoder.encode(memberDTO.passWord())); // Preenche como ADM
            membersModel.setSchoolModel(schoolModel); // Associa à escola

            memberRepository.save(membersModel);
        }
    }


}
