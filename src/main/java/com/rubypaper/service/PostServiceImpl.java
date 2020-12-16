package com.rubypaper.service;

import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.PostRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post registerPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Post savePost(Post post) {
        Post addPost = Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .postNum(post.getPostNum())
                .content(post.getContent())
                .regDate(post.getRegDate())
                .comments(post.getComments())
                .build();

        return postRepository.save(addPost);
    }

    @Override
    public List<Post> getpostList() {
        List<Post> postList = postRepository.findAll();
        List<Post> getPostList = new ArrayList<>();

        for(Post post : postList) {
            Post postBuilder = Post.builder()
                    .id(post.getId())
                    .postNum(post.getPostNum())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .comments(post.getComments())
                    .regDate(post.getRegDate())
                    .build();
            getPostList.add(postBuilder);
        }
        return getPostList;
    }

    @Override
    public Post getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        Post getPost = post.get();

        Post post1 = Post.builder()
                .id(getPost.getId())
                .postNum(getPost.getPostNum())
                .title(getPost.getTitle())
                .content(getPost.getContent())
                .comments(getPost.getComments())
                .regDate(getPost.getRegDate())
                .build();

        return post1;
    }



    @Override
    public void searchByCategory() {

    }

    @Override
    public void searchPost() {

    }

    

    @Override
    public Post updatePost(Long id, Post post) {
        Post oldPost = postRepository.findById(id).orElse(null);

        if(oldPost == null){
            try {
                throw new NotFoundException(id + " not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        oldPost.setContent(post.getContent());
        oldPost.setTitle(post.getTitle());
        // oldPost.setCategory(post.getCategory);

        return oldPost;
    }

    @Override
    public void deletePost(Long id) {
        Post oldPost = postRepository.findById(id).orElse(null);

        if(oldPost == null){
            try {
                throw new NotFoundException(id + " not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }



    }


}
