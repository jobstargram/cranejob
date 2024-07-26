package com.est.cranejob.comment.dto.response;

import com.est.cranejob.comment.domain.Comment;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  댓글의 내용을 일반 사용자가 볼 수 있는 형태로 응답하는 DTO<br>
 * DTO for {@link com.est.cranejob.comment.domain.Comment}
 */
@Data
@Builder
public class CommentResponse implements Serializable {

    private String content; // 댓글 내용
    private LocalDateTime createdAt; // 댓글 작성일
    private String nickname;

    public static CommentResponse toDTO(Comment comment) {
        return CommentResponse.builder()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .nickname(comment.getUser().getNickname())
                .build();
    }
}