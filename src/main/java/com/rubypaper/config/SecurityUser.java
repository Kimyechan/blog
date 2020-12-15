package com.rubypaper.config;

import com.rubypaper.domain.user.Role;
import com.rubypaper.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    public SecurityUser(User user) {
        super(user.getUserid(), "{noop}"+user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
        System.out.println("user.role" + user.getRole());
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<Role> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toSet());
    }

    public User getUser() {
        return user;
    }
};