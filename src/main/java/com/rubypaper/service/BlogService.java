package com.rubypaper.service;

public interface BlogService {

    /**
     * 블로그 검색
     * @param condition   : 검색 조건 (블로그 제목, 태그, 블로그 이름)
     * @param keyword : 검색어
     */
    void searchBlog(String keyword, String condition);

    /**
     * 블로그 등록
     */
    void registerBlog();

    void deleteBlog();

    void updateBlog();
}
