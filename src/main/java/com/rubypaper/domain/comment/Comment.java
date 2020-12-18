package com.rubypaper.domain.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rubypaper.domain.post.Post;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Builder
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue
    @Column(name="ID")
    private Long id; // 코멘트 작성자 아이디

    private String commContent;

    @CreatedDate
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private LocalDateTime regDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

//    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
//    @JoinColumn(name="USER_ID")
//    private User user;

    @Transient
    private Long postId;

    @Transient
    private String userName;

//    @Builder
//    public Comment(Long id, String content, LocalDateTime regDate) {
//        this.id = id;
//        this.commContent = content;
//        this.regDate = regDate;
//    }

    public Comment() { }


    /*    @ManyToOne(fetch=FetchType.LAZY)
    private Comment superComment; // 상위

    @OneToMany(mappedBy = "superComment", cascade = CascadeType.ALL)
    private List<Comment> subComment = new ArrayList<>(); // 하위

    private Integer level; // 댓글-댓글 단계

    private Boolean live;
*/

}
