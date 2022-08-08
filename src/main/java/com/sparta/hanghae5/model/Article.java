package com.sparta.hanghae5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity// DB 테이블 역할을 합니다.
public class Article {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column( nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userWriter;

    @Column(nullable = false)
    private String content;

    @Column
    private int likes;

    @ManyToOne
    @JoinColumn(name = "USERS_ID",nullable = false)
    private Users users;

    @OneToMany
    private List<Comment> commentList;

}
