package com.sparta.hanghae5.controller;

import com.sparta.hanghae5.dto.CommentRequestDto;
import com.sparta.hanghae5.dto.CommentResponseDto;
import com.sparta.hanghae5.dto.CommitRequestDto;
import com.sparta.hanghae5.model.Comment;
import com.sparta.hanghae5.model.Commit;
import com.sparta.hanghae5.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommitController {
    private final CommitService commitService;

    //대댓글 생성
    @PostMapping("/commit/{commentId}")
    public Commit createCommit(@PathVariable Long commentId, @RequestBody CommitRequestDto commitRequestDto){
        return commitService.createCommit(commentId,commitRequestDto);
    }

    //대댓글 조회
    @GetMapping("/commit/{commitId}")
    public Optional<Commit> readCommit(@PathVariable Long commitId){
        return commitService.readCommit(commitId);
    }

    //대댓글 수정 patch로 내용만 수정
    @PatchMapping("/commit/{commitId}")
    public Commit updateCommit(@PathVariable Long commitId,@RequestBody CommitRequestDto commitRequestDto){
        return commitService.updateCommit(commitId,commitRequestDto);
    }

    //대댓글 삭제
    @DeleteMapping("/commit/{commitId}")
    public Long deleteteCommit(@PathVariable Long commitId){
        commitService.deleteteCommit(commitId);
        return commitId;
    }





}
