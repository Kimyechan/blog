package com.rubypaper.domain.post;

import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.comment.Comment;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private LocalDateTime regDate;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Post() { }

    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Post(Long id) {
    }
}
