package com.rubypaper.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    private String userid;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;
}
