package com.rubypaper.service;

import com.rubypaper.domain.comment.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> searchComment(long id);

    Comment registerComment(Comment comment);

    List<Comment> updateComment(Comment comment,long commentId,long postId);

    void deleteComment(Long id);
}
