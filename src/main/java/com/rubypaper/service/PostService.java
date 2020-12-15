package com.rubypaper.service;

import com.rubypaper.domain.post.Post;

public interface PostService {

    /**
     * 카테고리별 글 목록 조회
     */
    void searchByCategory();

    void searchPost();

    Post registerPost(Post post);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);
}
