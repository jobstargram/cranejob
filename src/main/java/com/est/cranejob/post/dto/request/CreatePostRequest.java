package com.est.cranejob.post.dto.request;

import com.est.cranejob.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * 게시글 작성 요청을 보내는 DTO<br>
 * DTO for {@link com.est.cranejob.post.domain.Post}
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequest implements Serializable {

    @NotBlank(message = "제목을 작성해 주세요.")
    @Size(max = 50, message = "제목은 50자 내로 작성해 주세요.")
    private String title;

    @NotBlank(message = "내용을 작성해 주세요.")
    private String content;

    // private User user; userId는 서비스 계층에서 관리
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}