package com.rubypaper.repository;

import com.rubypaper.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.post where c.id = :commentId")
    Optional<Comment> findWithPostByCommentId(Long commentId);
}
