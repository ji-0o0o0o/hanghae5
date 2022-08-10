package com.sparta.hanghae5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleRequestDto {
    private String title;
    private String userWriter;
    private String content;
    private int likes;

}
