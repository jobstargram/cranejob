package com.est.cranejob.comment.dto.response;

import com.est.cranejob.comment.domain.Comment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 댓글의 내용을 관리자만 볼 수 있는 형태로 응답하는 DTO<br>
 * DTO for {@link com.est.cranejob.comment.domain.Comment}
 */
@Data
@Builder
public class CommentDetailResponse implements Serializable {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private LocalDateTime deletedAt;
    private Long user;
    private String nickname;
    private Long post;

    public static CommentDetailResponse toDTO(Comment comment) {
        return CommentDetailResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .isDeleted(comment.getIsDeleted())
                .deletedAt(comment.getDeletedAt())
                .user(comment.getUser().getId())
                .nickname(comment.getUser().getNickname())
                .post(comment.getPost().getId())
                .build();
    }
}