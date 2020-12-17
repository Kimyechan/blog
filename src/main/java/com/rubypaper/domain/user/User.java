package com.rubypaper.domain.user;

import com.rubypaper.domain.blog.Blog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Table(name = "BLOG_USER")
@Entity
@SequenceGenerator(
        name = "BLOG_USER_SEQ_GENERATOR",
        sequenceName = "BLOG_USER_SEQ",
        initialValue = 2, allocationSize = 1)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BLOG_USER_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "varchar(255) default 'ROLE_MEMBER'")
    private Role role=Role.ROLE_MEMBER;

//    @Column(columnDefinition = "boolean default true")
    private boolean enabled=true;

}
