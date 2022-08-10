package com.sparta.hanghae5.dto;

import com.sparta.hanghae5.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleResponseDto {

    private String title;
    private String userWriter;

    //응답해줄때는 어떻게 게시해줄지 엔티티에서 받아와서 생성자 작성해줘야함
    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.userWriter = article.getUserWriter();
    }
}
