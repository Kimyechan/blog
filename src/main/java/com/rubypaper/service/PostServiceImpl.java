package com.rubypaper.service;

import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.PostRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public void searchByCategory() {

    }

    @Override
    public void searchPost() {

    }

    @Override
    public Post registerPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        return postRepository.save(post);
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
