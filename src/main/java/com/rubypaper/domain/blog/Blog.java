package com.rubypaper.domain.blog;

import com.rubypaper.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Blog {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;

    private String title;

    private String tag;

    private Integer cnt;

    @Enumerated(EnumType.STRING)
    private BlogStatus status;

    private String fileName;
}
