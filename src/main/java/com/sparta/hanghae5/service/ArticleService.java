package com.sparta.hanghae5.service;

import com.sparta.hanghae5.dto.ArticleRequestDto;
import com.sparta.hanghae5.dto.ArticleResponseDto;
import com.sparta.hanghae5.model.Article;
import com.sparta.hanghae5.model.Users;
import com.sparta.hanghae5.repository.ArticleRepository;
import com.sparta.hanghae5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UsersRepository usersRepository;


//전체조회
    public  List<ArticleResponseDto> readAllArticle(){
        List<Article> articleList = articleRepository.findAll(); //레포에서 아이디를 찾아와서 articleList에 저장
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>(); // 응답해줄 List생성

        for (Article article:articleList) {
            articleResponseDtoList.add(new ArticleResponseDto(article));//왜 article이 대입이 안되지...
        }
        return articleResponseDtoList;

    }

//세부조회
    public Optional<Article> readArticle(Long articleId) {
        return articleRepository.findById(articleId);
    }


//게시글 작성
    public Article createArticle(ArticleRequestDto articleRequestDto) {
        Users users = usersRepository.findById(articleRequestDto.getUserWriter())
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));

        Article article = new Article(articleRequestDto,users);
        users.addArticle(article);
        return articleRepository.save(article);
    }

    // 게시글 수정
    @Transactional
    public Article UpdateArticle(Long articleId,ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));
        article.update(articleRequestDto);
        return  articleRepository.save(article);
    }


    @Transactional
    public Long DeleteteArticle(Long articleId) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("게시물이 없습니다."));
        Users users = usersRepository.findById(article.getUsers().getUsersname())
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));

        users.removeArticle(article);
        articleRepository.delete(article);
        return articleId;
    }

}
// 1. 무한회귀 정리하기
