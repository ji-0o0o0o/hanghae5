package com.sparta.hanghae5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity// DB 테이블 역할을 합니다.
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column( nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String userWriter;

    @Column(nullable = false)
    private String content;

    private int likes;

    @ManyToOne
    @JoinColumn(name = "USERS_ID",nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID",nullable = false)
    private Article article;

    @OneToMany
    private List<Commit> commitList;

}
