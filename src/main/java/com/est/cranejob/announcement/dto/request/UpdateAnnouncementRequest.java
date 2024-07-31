package com.est.cranejob.announcement.dto.request;

import com.est.cranejob.announcement.domain.Announcement;
import com.est.cranejob.announcement.dto.response.AnnouncementDetailResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.est.cranejob.announcement.domain.Announcement}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAnnouncementRequest implements Serializable {

    private Long id;

    @NotBlank(message = "공지사항의 제목은 빈 값이 들어갈 수 없습니다.")
    @Size(message = "제목은 50자 이내로 작성해 주세요", max = 50)
    private String title;

    @NotBlank(message = "공지사항의 내용은 빈 값이 들어갈 수 없습니다.")
    private String content;

    public static UpdateAnnouncementRequest toResponseDto(
        AnnouncementDetailResponse announcementDetailResponse) {
        return UpdateAnnouncementRequest.builder()
            .id(announcementDetailResponse.getId())
            .title(announcementDetailResponse.getTitle())
            .content(announcementDetailResponse.getContent())
            .build();
    }
}