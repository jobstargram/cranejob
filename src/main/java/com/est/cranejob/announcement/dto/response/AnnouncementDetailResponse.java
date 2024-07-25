package com.est.cranejob.announcement.dto.response;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 공지사항 상세를 반환하는 DTO<br>
 * DTO for {@link com.est.cranejob.announcement.domain.Announcement}
 */


@Data
@Builder
public class AnnouncementDetailResponse implements Serializable {
    private Long id; // 공지사항 ID
    private String title; // 공지사항 제목
    private String content; // 공지사항 내용
    private String userNickname; // 작성자 닉네임
    private LocalDateTime createdAt; // 작성 시간
    private LocalDateTime updatedAt; // 수정 시간
}
