//package com.SchoolNews.jefiro.br.service;
//
//import com.SchoolNews.jefiro.br.domain.MembersModel;
//import com.SchoolNews.jefiro.br.models.Comentarios;
//import com.SchoolNews.jefiro.br.models.NewsModel;
//import com.SchoolNews.jefiro.br.repository.CommentsRepository;
//import com.SchoolNews.jefiro.br.repository.LikeRepository;
//import com.SchoolNews.jefiro.br.repository.MemberRepository;
//import com.SchoolNews.jefiro.br.repository.NewsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LikeService {
//    @Autowired
//    LikeRepository repository;
//
//    @Autowired
//    CommentsRepository commentsRepository;
//
//    @Autowired
//    NewsRepository newsRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    public void LikeComent(String memberId, String comentID) {
//        if (memberId.isEmpty() || comentID.isEmpty()) {
//            return;
//        }
//
//        MembersModel member = memberRepository.findById(memberId).get();
//        Comentarios comentarios = commentsRepository.findById(comentID).get();
//    }
//}
