package com.rubypaper.controller;

import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.PostRepository;
import com.rubypaper.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostService postService;

    @GetMapping("/list")
    public String getAllPost(Model model) {
        List<Post> postList = postService.getpostList();
        model.addAttribute("postList", postList);

        return "blogmain";
    }

    @GetMapping("/{postNum}")
    public String getPost(@PathVariable("postNum") long postNum, Model model) {
        Post post = postService.getPost(postNum);
        model.addAttribute("post", post);

        return "blogmain_detail";
    }

    @PostMapping("/view/update/{id}")
    public String updatePostView(@PathVariable("id") long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);

        return "blogadmin_write";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable("id") long id, Post post) {
        postRepository.save(post);

        return "redirect:/post/view/{id}";

//
//        Optional<Post> post = postRepository.findById(id);
//
//        post.get().setTitle(newPost.getTitle());
//        post.get().setContent(newPost.getContent());
//        post.get().setCategory(newPost.getCategory());
//
//        postRepository.save(post.get());
//
//        return post.get();
    }

    @GetMapping("/view/newPost")
    @PostMapping
    public String registerPost() {
        return "blogadmin_write";
    }

    @PostMapping("/addPost.do")
    public String registerPost(Post post) {
        postService.savePost(post);
        return "redirect:/post/list";

//        Post newPost = postRepository.save(post);
//        return newPost;
    }

    @DeleteMapping("post/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/blogmain";
    }



}
