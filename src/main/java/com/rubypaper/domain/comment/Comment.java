package com.rubypaper.domain.comment;

import com.rubypaper.domain.post.Post;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String content;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;
}
