package com.sparta.hanghae5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommitRequestDto {
    private String userWriter;
    private String content;
    private int likes;
}
