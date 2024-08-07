package com.est.cranejob.post.dto.response;

import com.est.cranejob.comment.dto.response.CommentDetailResponse;
import com.est.cranejob.post.domain.Post;
import com.est.cranejob.user.domain.User;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 게시물 상세 정보를 관리자가 볼 수 있는 형태로 응답하는 DTO<br>
 * DTO for {@link com.est.cranejob.post.domain.Post}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAdminDetailResponse implements Serializable {
    private Long id; // 게시글 id
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private LocalDateTime createdAt; // 게시글 생성 일시
    private LocalDateTime updatedAt; // 게시글 마지막 수정 일시
    private boolean isDeleted; // 삭제 여부
    private LocalDateTime deletedAt; // 삭제 일시
    private String nickname; // 작성자 이름
    private List<CommentDetailResponse> commentDetailResponses; // 관리자가 볼 수 있는 댓글 생세

    public static PostAdminDetailResponse toDTO(Post post){
        List<CommentDetailResponse> commentDetailResponseList = post.getComments().stream().map(CommentDetailResponse::toDTO).toList();
        return PostAdminDetailResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .isDeleted(post.isDeleted())
                .deletedAt(post.getDeletedAt())
                .nickname(post.getUser().getNickname())
                .commentDetailResponses(commentDetailResponseList)
                .build();
    }
}