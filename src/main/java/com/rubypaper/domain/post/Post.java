package com.rubypaper.domain.post;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.comment.Comment;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="postNum") // 게시글 번호가 기준값
public class Post {
    @Id
    @GeneratedValue
    private Long id; // 사용자 계정

    @Column(nullable = false)
    private Long postNum; // 게시글 번호

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;

    @Builder
    public Post(Long id, Long postNum, String title, String content, List<Comment> comments, LocalDateTime regDate) {
        this.id = id;
        this.postNum = postNum;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.regDate = regDate;
    }

}
