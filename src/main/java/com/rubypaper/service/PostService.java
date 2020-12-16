package com.rubypaper.service;

import com.rubypaper.domain.post.Post;

import java.util.List;

public interface PostService {

    /**
     * 카테고리별 글 목록 조회
     */
    void searchByCategory();

    void searchPost();

    List<Post> getpostList();

    Post getPost(Long id);

    Post registerPost(Post post);

    Post savePost(Post post);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);
}
