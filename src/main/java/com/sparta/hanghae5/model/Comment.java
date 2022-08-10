package com.sparta.hanghae5.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.hanghae5.dto.ArticleRequestDto;
import com.sparta.hanghae5.dto.CommentRequestDto;
import com.sparta.hanghae5.dto.CommentResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity// DB 테이블 역할을 합니다.
public class Comment extends CommentResponseDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String userWriter;

    @Column(nullable = false)
    private String content;

    private int likes;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "USERS_ID", nullable = false)
    private Users users;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private Article article;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Commit> commitList;

    public Comment(CommentRequestDto commentRequestDto, Users users, Article article) {
        this.userWriter = commentRequestDto.getUserWriter();
        this.content = commentRequestDto.getContent();
        this.likes = commentRequestDto.getLikes();
        this.users = users;
        this.article = article;

    }

    public void update(CommentRequestDto commentRequestDto) {

        this.content = commentRequestDto.getContent();

    }

    public void addCommit(Commit commit) { //articleList에 article넣어준다.
        this.commitList.add(commit);
    }

    public void removeCommit(Commit commit) { //articleList에 지워준다.
        this.commitList.remove(commit);
    }
}