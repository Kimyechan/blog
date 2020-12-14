package com.rubypaper.service;

public interface UserService {

    void login();

    void logout();

    void signUp();

    /**
     * 회원탈퇴
     */
    void deleteUser();
    /**
     * 관리자 사용자 구분
     */
    void checkRole();
}
