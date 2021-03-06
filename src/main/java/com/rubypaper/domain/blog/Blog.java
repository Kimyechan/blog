package com.rubypaper.domain.blog;

import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.post.Post;
import com.rubypaper.domain.user.User;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID"}))
@SequenceGenerator(
        name = "BLOG_SEQ_GENERATOR",
        sequenceName = "BLOG_SEQ",
        initialValue = 1, allocationSize = 1)
public class Blog {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BLOG_SEQ_GENERATOR")
    @Column(name = "BLOG_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;

    private String title;

    private String tag;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer cntDisplayPost;

    @Enumerated(EnumType.STRING)
    private BlogStatus status;

    private String fileName;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();
}
