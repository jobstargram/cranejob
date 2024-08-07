package com.est.cranejob.post.dto.response;

import com.est.cranejob.announcement.domain.Announcement;
import com.est.cranejob.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 게시글을 요약한 상태로 수 있는 형태로 응답하는 DTO<br>
 * DTo for {@link com.est.cranejob.post.domain.Post}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSummaryResponse implements Serializable {
    private Long id; // 게시글 id
    private String title; // 게시글 제목
    private LocalDateTime createdAt; // 게시글 생성 일시
    private String nickname; // 게시글 작성자
    private String type;

    public static PostSummaryResponse toDTO(Post post){
        return PostSummaryResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .nickname(post.getUser().getNickname())
                .type("post")
                .build();
    }

    public static PostSummaryResponse fromAnnouncement(Announcement announcement) {
        return PostSummaryResponse.builder()
            .id(announcement.getId())
            .title(announcement.getTitle())
            .createdAt(announcement.getCreatedAt())
            .nickname(announcement.getUser().getNickname())
            .type("announcement")
            .build();
    }
}