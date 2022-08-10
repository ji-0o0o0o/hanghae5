package com.sparta.hanghae5.controller;

import com.sparta.hanghae5.dto.ArticleRequestDto;
import com.sparta.hanghae5.dto.ArticleResponseDto;
import com.sparta.hanghae5.model.Article;
import com.sparta.hanghae5.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;


    //전체 포스트 조회, content 빼고 받아와야하니까  Response로
    @GetMapping("/article")
    public List<ArticleResponseDto> readAllArticle(){

        return articleService.readAllArticle();
    }

    //상세 포스트 조회
    @GetMapping("/article/{articleId}")
    public Optional<Article> readArticle(@PathVariable Long articleId){
        return articleService.readArticle(articleId);
    }
//     포스트 입력
    @PostMapping("/article")
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.createArticle(articleRequestDto);
    }


    //포스트 업그레이드
    @PutMapping("/article/{articleId}")
    public Article UpdateArticle(@PathVariable Long articleId,@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.UpdateArticle(articleId,articleRequestDto);
    }


    // 포스트 지우기
    @DeleteMapping("/article/{articleId}")
    public Long DeleteteArticle(@PathVariable Long articleId){
        return articleService.DeleteteArticle(articleId);
    }

}
