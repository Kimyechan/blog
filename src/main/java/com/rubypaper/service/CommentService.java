package com.rubypaper.service;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;
import com.rubypaper.domain.user.User;

import java.util.List;

public interface CommentService {
    Comment saveComment (String content, Post post, User user);

    List<Comment> getCommentList();

    Comment readComm(Long id);

    void deleteComment(Long commentId);

    // List<Comment> searchComment(long id);

}
