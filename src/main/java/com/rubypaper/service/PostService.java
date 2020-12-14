package com.rubypaper.service;

public interface PostService {

    /**
     * 카테고리별 글 목록 조회
     */
    void searchByCategory();

    void searchPost();

    void registerPost();

    void updatePost();

    void deletePost();
}
