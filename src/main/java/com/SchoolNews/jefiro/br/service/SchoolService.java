package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.infra.security.Exception.UnauthorizedException;
import com.SchoolNews.jefiro.br.models.SchoolModel;
import com.SchoolNews.jefiro.br.models.dto.*;
import com.SchoolNews.jefiro.br.models.enumm.MemberRole;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.repository.SchoolRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public MemberResponseDTO newSchool(SchoolModelDTO dto) {
        SchoolModel schoolModel = new SchoolModel(dto);

        var memberDTO = dto.memberMode();

        if (memberDTO != null) {

            MembersModel membersModel = new MembersModel();
            membersModel.memberModelADM(memberDTO, passwordEncoder.encode(memberDTO.passWord()));
            membersModel.setSchoolModel(schoolModel);

            memberRepository.save(membersModel);
        }

        var c = schoolRepository.save(schoolModel);
        return new MemberResponseDTO(c);
    }

    public loginSchoolResponse loginSchool(@Valid SchoolLoginDTO date) {

        var membersModel = memberRepository.findByEmailAndSchoolModel_SchoolName(date.email(), date.schoolName());

        if (membersModel == null) {
            throw new UnauthorizedException("Usuário não encontrado ou credenciais inválidas");
        }

        var userNamePassWord = new UsernamePasswordAuthenticationToken(date.email(), date.passWord());

        var auth = this.authenticationManager.authenticate(userNamePassWord);

        var authenticatedMember = (MembersModel) auth.getPrincipal();
        if (authenticatedMember.getRole() != MemberRole.ADMIN){
            throw new UnauthorizedException("Somente administradores podem fazer login");
        }


        var token = tokenService.generateToken(authenticatedMember);


        return new loginSchoolResponse(new MemberDTO(memberRepository.findById(authenticatedMember.get_id()).get()),token, membersModel.getSchoolModel().get_id());
    }

    public SchoolModel findByID(String _id) {
        return schoolRepository.findById(_id).get();
    }


}
