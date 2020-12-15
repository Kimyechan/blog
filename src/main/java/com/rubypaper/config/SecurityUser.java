package com.rubypaper.config;

import com.rubypaper.domain.user.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        super(user.getUserid(), "{noop}"+user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
};