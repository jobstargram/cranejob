package com.est.cranejob.post.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 게시글을 요약한 상태로 수 있는 형태로 응답하는 DTO<br>
 * DTo for {@link com.est.cranejob.post.domain.Post}
 */
@Data
@Builder
public class PostSummaryResponse implements Serializable {
    private Long id; // 게시글 id
    private String title; // 게시글 제목
    private LocalDateTime createdAt; // 게시글 생성 일시
    private String userNickname; // 게시글 작성자
}