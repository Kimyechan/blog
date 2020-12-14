package com.rubypaper.domain.category;

import com.rubypaper.domain.blog.Blog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;

    private String name;

    private String displayType;

    private Integer cnt;

    private LocalDate createDate;

    private LocalDate modifiedDate;
}
