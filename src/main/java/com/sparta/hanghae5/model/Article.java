package com.sparta.hanghae5.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.hanghae5.dto.ArticleRequestDto;
import lombok.Builder;
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

    @Column//셍략가
    private int likes;
    @ManyToOne
    @JsonBackReference  // or   @JsonIgnore
    @JoinColumn(name = "USERS_ID",nullable = false)
    private Users users;


    @OneToMany(cascade = CascadeType.ALL)// 부모가 변할때 같이 변한다.remove, refresh 등 all은 이거다
    @JsonManagedReference
    private List<Comment> commentList;

    //  @Builder -> 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함, 이걸 꼭 써야할까?
    // requestdto 쓰는 이유는 데이터를 레포지토리에 넣어주기위한 목적
    // dto에서 article의 title, userWriter, content가져오고 user에서 username가져와야함
    // 여기에 유저정보 넣는거
    public Article(ArticleRequestDto articleRequestDto, Users users) {
        this.title = articleRequestDto.getTitle();
        this.userWriter = users.getUsersname();
        this.content = articleRequestDto.getContent();
        this.likes = articleRequestDto.getLikes();
        this.users = users;

    }


    public void update(ArticleRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.userWriter = articleRequestDto.getUserWriter();
        this.content = articleRequestDto.getContent();
        this.likes = articleRequestDto.getLikes();
    }
    public void addComment(Comment comment) { //articleList에 article넣어준다.
        this.commentList.add(comment);
    }

    public void removeComment(Comment comment) { //articleList에 지워준다.
        this.commentList.remove(comment);
    }
}
