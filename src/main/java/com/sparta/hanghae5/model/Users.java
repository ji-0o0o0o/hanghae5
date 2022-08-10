package com.sparta.hanghae5.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.hanghae5.dto.UsersRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Users {


    //pK 임의적으로 사용하니까
    @Id
    @Column( nullable = false, unique = true)
    private String usersname;


    @Column(nullable = false)
    private String password;

    // 한명의 유저가 여러개의 포스트,댓글, 대댓글을 달 수 있으니까
    @OneToMany(mappedBy = "users")//유저쪽에만 해줌, 양방향 매핑 -> join테이블 안만들어줌, 다른 아티클간의 변화 감지한다.나중에
    @JsonManagedReference
    private List<Article> articleList;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference
    private List<Comment> commentList;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference
    private List<Commit> commitList;

    @Builder
    public Users(UsersRequestDto requestDto) {
        this.usersname = requestDto.getUsersname();
        this.password = requestDto.getPassword();
    }


    public void addArticle(Article article) { //articleList에 article넣어준다.
    this.articleList.add(article);
    }

    public void removeArticle(Article article) { //articleList에 지워준다.
        this.articleList.remove(article);
    }

    public void addComment(Comment comment) { this.commentList.add(comment);
    }

    public void removeComment(Comment comment) { //articleList에 지워준다.
        this.commentList.remove(comment);
    }

    public void addCommit(Commit commit) { this.commitList.add(commit);
    }

    public void removeCommit(Commit commit) { //articleList에 지워준다.
        this.commitList.remove(commit);
    }
}
