package com.rubypaper.controller;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;

import com.rubypaper.domain.user.User;

import com.rubypaper.repository.PostRepository;
import com.rubypaper.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private final CommentService commentService;

    @Autowired
    private final PostRepository postRepository;

    // 코멘트 작성
    @PostMapping("/addComment")
    public String registerComment(Long postId, String commentName, String comment, Post post, User user) {
        user.setName(commentName);
        post.setId(postId);
        commentService.saveComment(comment, post, user);

        return "redirect:/post/" + postId;
    }

    // 코멘트 리스트
    @GetMapping("/list")
    public String CommentList(Model model, Long postId) {
        List<Comment> commentList = commentService.getCommentList();
        model.addAttribute("commentList", commentList);

        return "redirect:/post" + postId;
    }

    // 코멘트 수정
    @GetMapping("/{commentNum}")
    public String updateComment(@PathVariable("commentNum") String comNum, Model model) {
        long commentNum = Long.parseLong(comNum);
        Comment comment = commentService.readComm(commentNum);
        model.addAttribute("post", comment.getPost());
        model.addAttribute("comment", comment);

        return "blogadmin_update";
    }

    @PostMapping("/{commentNum}")
    public String updateComment(Long postId, String commentName, String comment, Post post, User user) {
        user.setName(commentName);
        post.setId(postId);
        commentService.saveComment(comment, post, user);

        return "redirect:/post/" + postId;
    }

    // 코멘트 삭제
    @GetMapping("/delete/{commentNum}")
    public String deleteComment(@PathVariable("commentNum") String id) {
        long commentNum = Long.parseLong(id);
        commentService.deleteComment(commentNum);
        return "redirect:/post/list";
    }

}
