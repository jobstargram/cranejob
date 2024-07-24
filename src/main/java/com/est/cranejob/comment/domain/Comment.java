package com.est.cranejob.comment.domain;

import com.est.cranejob.post.domain.Post;
import com.est.cranejob.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "Comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // 댓글 작성자
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 댓글을 작성한 게시글
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment-content")
    private String commentContent;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
