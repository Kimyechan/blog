package com.rubypaper.domain.category;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "CATEGORY_SEQ_GENERATOR",
        sequenceName = "CATEGORY_SEQ",
        initialValue = 1, allocationSize = 1)
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "CATEGORY_SEQ_GENERATOR")
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;

    private String name;

    private String displayType;

    private Integer cnt;

    private String description;

    private LocalDate createDate;

    private LocalDate modifiedDate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();
}
