package com.sparta.hanghae5.service;

import com.sparta.hanghae5.dto.CommitRequestDto;
import com.sparta.hanghae5.model.Article;
import com.sparta.hanghae5.model.Comment;
import com.sparta.hanghae5.model.Commit;
import com.sparta.hanghae5.model.Users;
import com.sparta.hanghae5.repository.ArticleRepository;
import com.sparta.hanghae5.repository.CommentRepository;
import com.sparta.hanghae5.repository.CommitRepository;
import com.sparta.hanghae5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommitService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UsersRepository usersRepository;
    private final CommitRepository commitRepository;


    public Commit createCommit(Long commentId, CommitRequestDto commitRequestDto) {
        Users users = usersRepository.findById(commitRequestDto.getUserWriter())
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));

        Commit commit = new Commit(commitRequestDto,users,comment);
        users.addCommit(commit);
        comment.addCommit(commit);
        return commitRepository.save(commit);

    }

    public Optional<Commit> readCommit(Long commitId) {
        return commitRepository.findById(commitId);
    }
    @Transactional
    public Commit updateCommit(Long commitId, CommitRequestDto commitRequestDto) {
        Commit commit = commitRepository.findById(commitId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));
        commit.update(commitRequestDto);
        return commitRepository.save(commit);
    }

    public Long deleteteCommit(Long commitId) {
        Commit commit = commitRepository.findById(commitId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));
        Comment comment = commentRepository.findById(commit.getComment().getId())
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));
        Users users = usersRepository.findById(commit.getUsers().getUsersname())//commit.getUsers().getUsersname() 도 가능,게시물을 쓴유저를 찾즈느다.
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));
//        System.out.println(users.getUsersname());//확인해보기

        users.removeCommit(commit);
        comment.removeCommit(commit);
        commitRepository.delete(commit);
        return commitId;

    }
}

