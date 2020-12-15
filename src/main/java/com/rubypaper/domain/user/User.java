package com.rubypaper.domain.user;

import com.rubypaper.domain.blog.Blog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Table(name = "BLOG_USER")
@Entity
public class User{
    @Id
    @GeneratedValue
    private Long id;

    private String userid;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "varchar(255) default 'ROLE_MEMBER'")
    private Role role=Role.ROLE_MEMBER;

//    @Column(columnDefinition = "boolean default true")
    private boolean enabled=true;
}
