package com.rubypaper.service;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> searchComment(long id) {
        return null;
    }

    @Override
    public Comment registerComment(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> updateComment(Comment comment,long commentId,long postId) {
        return null;
    }

    @Override
    public void deleteComment(Long id) {

    }

}
