package com.rubypaper.service;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;
import com.rubypaper.domain.user.User;
import com.rubypaper.repository.CommentRepository;
import com.rubypaper.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final PostRepository postRepository;

    // 코멘트 작성, 수정
    @Override
    public Comment saveComment(String content, Post post, User user) {
        Comment comment = new Comment();

        comment.setRegDate(LocalDateTime.now());

        Comment newComment = Comment.builder()
                .id(comment.getId())
                .commContent(content)
                //.User.(user)
                .post(post)
                .regDate(comment.getRegDate())
                .build();

        return commentRepository.save(newComment);
    }

    // 코멘트 리스트
    @Override
    public List<Comment> getCommentList() {
        List<Comment> commList = commentRepository.findAll();
        List<Comment> getCommentList = new ArrayList<>();
        // int totalCommentCnt // 총 댓글 수

        for(Comment comment : commList) {
            Comment costBuilder = Comment.builder()
                    .id(comment.getId())
                    .commContent(comment.getCommContent())
                    .userName(comment.getUserName())
                    .regDate(comment.getRegDate())
                    .build();
            getCommentList.add(costBuilder);
        }
        return getCommentList;
    }

    @Override
    public Comment readComm(Long id){
        Optional<Comment> findComment = commentRepository.findWithPostByCommentId(id);
        return findComment.orElse(null);
    }

    // 코멘트 삭제
    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    /*
    // 코멘트 수정하거나 답글 달 때
    comment.setRegDate(LocalDateTime.now());
    return commentRepository.save(comment);

    Comment newComment = new Comment();

    if (comment.getId() == null) {
        newComment.setLevel(1);
    } else {
        long supCommentId = Long.parseLong(String.valueOf(comment.getId()));
        Comment supComment = commentRepository.findById(supCommentId);

        if (supComment.getLive()) {
            throw  new RuntimeException("원 댓글 삭제되어서 댓글 달기 불가능 ");
        }

        newComment.setLevel(supComment.getLevel() + 1);
        newComment.setSuperComment(supComment);
        newComment.getSubComment().add(newComment);
    } // end else

    newComment.setCommContent(comment.getCommContent());
    newComment.setPost(post);

    newComment.setCommName(user.getName());

    newComment.setLive(true);
    commentRepository.save(newComment);

    return null;
}*/


//    @Override
//    public List<Comment> searchComment(long id) {
//        return null;
//    }

}
