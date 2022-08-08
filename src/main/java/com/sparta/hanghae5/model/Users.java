package com.sparta.hanghae5.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Users {


    //FK 임의적으로 사용하니까
    @Id
    @Column( nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    private String password;

    // 한명의 유저가 여러개의 포스트,댓글, 대댓글을 달 수 있으니까
    @OneToMany
    private List<Post> postList;

    @OneToMany
    private List<Comment> commentList;

    @OneToMany
    private List<Commit> commitList;

}
