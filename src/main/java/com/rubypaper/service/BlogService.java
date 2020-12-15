package com.rubypaper.service;

import com.rubypaper.domain.blog.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {

    Page<Blog> blogListNonCondition();
    /**
     * 블로그 검색
     * @param searchKeyword : 검색어
     * @param searchCondition   : 검색 조건 (블로그 제목, 태그, 블로그 이름)
     * @return
     */
    List<Blog> searchBlog(String searchCondition , String searchKeyword);

    /**
     * 블로그 등록
     */
    void createBlog(String title, String username);

    void deleteBlog();

    void updateBlog();

    Blog findMyBlog(Long userId);
}
