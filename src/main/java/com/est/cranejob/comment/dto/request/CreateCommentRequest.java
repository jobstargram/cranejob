package com.est.cranejob.comment.dto.request;

import com.est.cranejob.comment.domain.Comment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 댓글 요청을 보내는 DTO<br>
 * DTO for {@link com.est.cranejob.comment.domain.Comment}
 */

@Data
@Builder
public class CreateCommentRequest implements Serializable {

    @NotNull(message = "댓글의 내용은 비어있을 수 없습니다.")
    @Size(max = 100, message = "댓글은 100자 이내로 작성 되어야 합니다.")
    private String content;

    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .build();
    }
}