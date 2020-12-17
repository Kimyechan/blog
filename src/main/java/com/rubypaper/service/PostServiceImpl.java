package com.rubypaper.service;

import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;

    // 게시글 목록
    @Override
    public List<Post> getpostList() {
        List<Post> postList = postRepository.findAll();
        List<Post> getPostList = new ArrayList<>();
        // int totalPostCnt // 게시글 수

        for(Post post : postList) {
            Post postBuilder = Post.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .commentList(post.getCommentList())
                    .regDate(post.getRegDate())
                    .build();
            getPostList.add(postBuilder);
        }
        return getPostList;
    }

    // 게시글 상세보기
    @Override
    public Post readPost(Long id){
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = postOptional.get();

        Post postBuilder = Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .commentList(post.getCommentList())
                .blog(post.getBlog())
                .regDate(post.getRegDate())
                .build();

        return postBuilder;
    }

    // 게시글 등록, 수정
    @Override
    public Post savePost(Post post) {
        post.setRegDate(LocalDateTime.now());
        Post addPost = Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .regDate(post.getRegDate())
                .commentList(post.getCommentList())
                .build();
        return postRepository.save(addPost);
    }

    // 게시글 삭제
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
        /*Post oldPost = postRepository.findById(id);
        // 게시글 수정에 따른 카테고리 내 게시글 카운트
        // Category category = oldPost.getCategory();
        // categoryRepository.updatePostCnt(category.getId(), category.getPostCnt() -1);

        if(oldPost == null){
            try {
                throw new NotFoundException(id + " not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }*/
    }

}
