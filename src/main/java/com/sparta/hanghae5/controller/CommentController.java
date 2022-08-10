package com.sparta.hanghae5.controller;

import com.sparta.hanghae5.dto.CommentRequestDto;
import com.sparta.hanghae5.dto.CommentResponseDto;
import com.sparta.hanghae5.model.Article;
import com.sparta.hanghae5.model.Comment;
import com.sparta.hanghae5.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/comment/{articleId}")
    public Comment createComment(@PathVariable Long articleId,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(articleId,commentRequestDto);
    }

    //전체댓글 조회
    @GetMapping("/comments/{articleId}")
    public List<CommentResponseDto> readAllComment(@PathVariable Long articleId){
        return commentService.readAllComment(articleId);
    }
   // 상세 댓글 조회(하나씩 꺼내오기)
    @GetMapping("/comment/{commentId}")
    public Optional<Comment> readComment(@PathVariable Long commentId){
        return commentService.readComment(commentId);
    }
    //댓글 수정 patch 써서 content 만 고쳐주기 ??
    @PatchMapping("/comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.updateComment(commentId,commentRequestDto);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public Long deleteteComment(@PathVariable Long commentId){
        commentService.deleteteComment(commentId);
        return commentId;
    }


}
