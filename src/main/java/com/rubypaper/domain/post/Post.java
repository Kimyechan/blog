package com.rubypaper.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.comment.Comment;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name = "POST_SEQ_GENERATOR",
        sequenceName = "POST_SEQ",
        initialValue = 1, allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "POST_SEQ_GENERATOR")
    @Column(name = "POST_ID")
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name="POST_COUNT")
    private Integer postCnt;

    @CreatedDate
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;

//    @Builder
//    public Post(Long id, String title, String content, List<Comment> comments, LocalDateTime regDate) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.commentList = comments;
//        this.regDate = regDate;
//    }

    @Builder
    public Post(Long id, String title, String content,
                LocalDateTime regDate, Category category, Blog blog) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.category = category;
        this.blog = blog;
    }

}
