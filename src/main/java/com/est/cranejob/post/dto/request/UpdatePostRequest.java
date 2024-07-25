package com.est.cranejob.post.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 게시글 수정 요청을 보내는 DTO<br>
 * DTO for {@link com.est.cranejob.post.domain.Post}
 */
@Data
@Builder
public class UpdatePostRequest implements Serializable {
    @NotNull(message = "제목을 입력해 주세요.")
    @Size(message = "제목은 50자 내로 작성해 주세요.", max = 50)
    private String title;

    @NotNull(message = "내용을 작성해 주세요.")
    private String content;
}