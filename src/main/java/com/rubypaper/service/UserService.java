package com.rubypaper.service;

import com.rubypaper.domain.user.User;

public interface UserService {

    void login();

    void logout();

    boolean signUp(User user);

    /**
     * 회원탈퇴
     */
    void deleteUser(User user);
    /**
     * 관리자 사용자 구분
     */
    void checkRole();

    String idCheck(String id);
}
