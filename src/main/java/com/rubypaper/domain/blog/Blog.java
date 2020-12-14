package com.rubypaper.domain.blog;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Blog {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String tag;

    private Integer cnt;

    @Enumerated(EnumType.STRING)
    private BlogStatus status;

    private String fileName;
}
