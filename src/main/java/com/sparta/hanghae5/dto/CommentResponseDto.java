package com.sparta.hanghae5.dto;

import com.sparta.hanghae5.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String userWriter;
    private String content;


    public CommentResponseDto(Comment comment) {
        this.userWriter = comment.getUserWriter();
        this.content = comment.getContent();

    }
}
