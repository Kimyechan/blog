package com.rubypaper.service;

import com.rubypaper.domain.user.User;

public interface UserService {

    void login();

    void logout();

    String signUp(User user);

    /**
     * 회원탈퇴
     */
    void withdrawal(User user);
    /**
     * 관리자 사용자 구분
     */
    void checkRole();


    void updateUser(User user);
}
