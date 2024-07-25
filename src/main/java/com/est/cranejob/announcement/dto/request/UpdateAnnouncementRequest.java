package com.est.cranejob.announcement.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.est.cranejob.announcement.domain.Announcement}
 */
@Data
@Builder
public class UpdateAnnouncementRequest implements Serializable {
    @NotNull(message = "공지사항의 제목은 빈 값이 들어갈 수 없습니다.")
    @Size(message = "제목은 50자 이내로 작성해 주세요", max = 50)
    private String title;
    @NotNull(message = "공지사항의 내용은 빈 값이 들어갈 수 없습니다.")
    private String content;
}