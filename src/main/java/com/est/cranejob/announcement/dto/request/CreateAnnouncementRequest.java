package com.est.cranejob.announcement.dto.request;

import com.est.cranejob.announcement.domain.Announcement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * 공지사항 생성을 요청하는 DTO<br>
 * DTO for {@link com.est.cranejob.announcement.domain.Announcement}
 */
@Data
@Builder
public class CreateAnnouncementRequest implements Serializable{

    @NotNull(message = "공지사항의 제목은 빈값이 들어갈 수 없습니다.")
    @Size(max = 50, message = "제목은 50자 이내로 작성해 주세요.")
    private String title;

    @NotNull(message = "공지사항의 내용은 빈값이 들어갈 수 없습니다.")
    private String content;

    public Announcement toEntity(){
        return Announcement.builder()
                .title(title)
                .content(content)
                .build();
    }
}