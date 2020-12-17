package com.rubypaper.controller;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final PostRepository postRepository;

    @GetMapping("/blogmain_detail/{postId}")
    public List<Comment> getPostComment(@PathVariable long postId) {
        Post post = postRepository.findById(postId).get();
        return commentRepository.findCommentsByPost(post);
    }

    @PostMapping("/blogadmin_write")
    public Comment registerComment(@PathVariable long id, @RequestBody Comment comment) {
        Optional<Post> post = postRepository.findById(id);
        comment.setPost(post.get());
        commentRepository.save(comment);
        return comment;
    }

    @PostMapping("/blogmain_detail/{postId}")
    public Comment updateComment(@PathVariable long id, @PathVariable long postId, @RequestBody Comment comment){
        Optional<Post> post = postRepository.findById(postId);
        comment.setPost(post.get());

        Comment newComment = commentRepository.findById(id).get();
        newComment.setContent(comment.getContent());
        newComment.setUsername(comment.getUsername());

        return newComment;
    }

    // @GetMapping("/blogmain_detail/{postId}")
    public String deleteComment(@PathVariable Long id, @PathVariable Long postId){
        commentRepository.deleteById(id);
        return "blogmain_detail";
    }

}
