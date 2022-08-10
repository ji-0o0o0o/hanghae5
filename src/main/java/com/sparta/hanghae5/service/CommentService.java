package com.sparta.hanghae5.service;

import com.sparta.hanghae5.dto.CommentRequestDto;
import com.sparta.hanghae5.dto.CommentResponseDto;
import com.sparta.hanghae5.model.Article;
import com.sparta.hanghae5.model.Comment;
import com.sparta.hanghae5.model.Users;
import com.sparta.hanghae5.repository.ArticleRepository;
import com.sparta.hanghae5.repository.CommentRepository;
import com.sparta.hanghae5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UsersRepository usersRepository;

    public Comment createComment(Long articleId, CommentRequestDto commentRequestDto) {
        Users users = usersRepository.findById(commentRequestDto.getUserWriter())
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("게시물이 없습니다."));

        Comment comment = new Comment(commentRequestDto,users,article);
        users.addComment(comment);
        article.addComment(comment);
        return commentRepository.save(comment);
    }


    public List<CommentResponseDto> readAllComment() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment:commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment)); // response에 생성자 넣었는데 왜 안돼지..
        }
        return commentResponseDtoList;
    }
    public Optional<Comment> readComment(Long commentId) {
        return commentRepository.findById(commentId);
}

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));
        comment.update(commentRequestDto);
        return commentRepository.save(comment);

    }

    public Long deleteteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다. "));
        Article article = articleRepository.findById(comment.getArticle().getId()) //comment.getArticle().getId()
                .orElseThrow(()-> new IllegalArgumentException("게시물이 없습니다."));
        Users users = usersRepository.findById(comment.getUsers().getUsersname())
                .orElseThrow(()-> new IllegalArgumentException("아이디가 없습니다"));

        users.removeComment(comment);
        article.removeComment(comment);
        commentRepository.delete(comment);
        return commentId;

    }
}
