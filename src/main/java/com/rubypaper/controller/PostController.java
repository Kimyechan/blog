package com.rubypaper.controller;

import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.PostRepository;
import com.rubypaper.service.PostService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private final PostRepository postRepository;

    @GetMapping("/")
    public String hello() {
        return "main page";
    }

    @GetMapping("/blogmain_detail")
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @PostMapping("/blogmain")
    public Post getPost(@PathVariable long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.get();
    }

    @PostMapping("/blogmain_detail/{id}")
    public Post updatePost(@PathVariable long id, @RequestBody Post newPost) {
        Optional<Post> post = postRepository.findById(id);

        post.get().setTitle(newPost.getTitle());
        post.get().setContent(newPost.getContent());
        post.get().setCategory(newPost.getCategory());

        postRepository.save(post.get());

        return post.get();
    }

    @PutMapping("/blogadmin_write")
    public Post registerPost(@RequestBody Post post) {
        Post newPost = postRepository.save(post);
        return newPost;
    }

    @PostMapping("{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/blogmain";
    }



}
