package com.est.cranejob.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * 댓글 수정 요청을 보내는 DTO<br>
 * DTO for {@link com.est.cranejob.comment.domain.Comment}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCommentRequest implements Serializable {

    @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
    @Size(max = 100, message = "댓글은 100자 이내로 작성 되어야 합니다.")
    private String content;
}