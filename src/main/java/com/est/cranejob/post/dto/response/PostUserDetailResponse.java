package com.est.cranejob.post.dto.response;

import com.est.cranejob.comment.dto.response.CommentResponse;
import com.est.cranejob.post.domain.Post;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 게시글의 상세 정보를 일반 사용자가 볼 수 있는 형태로 응답하는 DTO<br>
 * DTO for {@link com.est.cranejob.post.domain.Post}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserDetailResponse implements Serializable {
    private Long id; // 게시글 id
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private List<CommentResponse> commentResponses; // 게시글에 작성된 댓글
    private String nickname; // 게시글 작성자
    private String username; // 게시글 작성자 확인

    public static PostUserDetailResponse toDTO(Post post){
        List<CommentResponse> commentResponseList = post.getComments().stream().map(CommentResponse::toDTO)
                .toList();
        return PostUserDetailResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .commentResponses(commentResponseList)
                .nickname(post.getUser().getNickname())
                .username(post.getUser().getUsername())
                .build();
    }

}