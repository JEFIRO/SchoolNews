package com.SchoolNews.jefiro.br.service;

import com.SchoolNews.jefiro.br.domain.MembersModel;
import com.SchoolNews.jefiro.br.models.NewsModel;
import com.SchoolNews.jefiro.br.models.dto.HomeDTO;
import com.SchoolNews.jefiro.br.models.dto.NewsModelDTO;
import com.SchoolNews.jefiro.br.repository.MemberRepository;
import com.SchoolNews.jefiro.br.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    NewsRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    public NewsModel nNews(NewsModelDTO date) {
        if (date == null) {
            return null;
        }
        var _id = date.member_id();
        Optional<MembersModel> membersModel = memberRepository.findById(_id);
        if (!membersModel.isPresent()) {
            return null;
        }

        NewsModel newsModel = new NewsModel(date, membersModel.get());

        repository.save(newsModel);
        return newsModel;
    }

    public List<HomeDTO> homePage() {
        return repository.findAll().stream().map(
                home -> new HomeDTO(home.getMembers().get_id(), home.get_id(), home.getTitle(),
                        home.getDescription(), home.getImageMain(), home.getImageMainDescription(), home.getDatePublished(),
                        home.getAuthor(), home.getLead(), home.getContent(), home.getDateUpdated())
        ).toList();
    }

}
