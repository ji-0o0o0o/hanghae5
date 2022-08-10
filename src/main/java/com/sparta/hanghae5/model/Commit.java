package com.sparta.hanghae5.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.hanghae5.dto.CommitRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity// DB 테이블 역할을 합니다.
public class Commit {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String userWriter;

    @Column(nullable = false)
    private String content;

    private int likes;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "USERS_ID",nullable = false)
    private Users users;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "COMMENT_ID",nullable = false)
    private Comment comment;

    public Commit(CommitRequestDto commitRequestDto, Users users, Comment comment) {
        this.userWriter = commitRequestDto.getUserWriter();
        this.content = commitRequestDto.getContent();
        this.likes = commitRequestDto.getLikes();
        this.users = users;
        this.comment = comment;

    }


    public void update(CommitRequestDto commentRequestDto) {

        this.content = commentRequestDto.getContent();
    }


}
