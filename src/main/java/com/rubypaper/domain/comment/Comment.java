package com.rubypaper.domain.comment;

import com.rubypaper.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id; // 코멘트 작성자 아이디

    private LocalDate regDate; // 코멘트 작성일

    private String username; // 코멘트 작성자 이름

    private String content; // 코멘트 내용

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;
}
