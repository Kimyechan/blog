package com.rubypaper.repository;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost(Post post);
}
