package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.Comentarios;
import com.SchoolNews.jefiro.br.models.NewsModel;
import com.SchoolNews.jefiro.br.repository.CommentsRepository;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ComentsService {
    @Autowired
    CommentsRepository repository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    NewsRepository newsRepository;

    public void postComment(String newsId, String memberId, String content) {
        if (content.length() > 140 || content.length() <= 0) {
            return;
        }

        MembersModel membersModel = memberRepository.findById(memberId).get();
        NewsModel newsModel = newsRepository.findById(newsId).get();

        repository.save(new Comentarios(membersModel, newsModel, content));
    }

    public void deleteComment(String commentId) throws NoSuchElementException {
        if (commentId.isEmpty()) {
            throw new NoSuchElementException("");
        }

        var coment = repository.findById(commentId).get();
        coment.setIsActive(false);
        repository.save(coment);
    }

}
