package com.est.cranejob.announcement.dto.response;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 공지사항 요약을 나타내는 DTO<br>
 * DTO for {@link com.est.cranejob.announcement.domain.Announcement}
 */
@Data
@Builder
public class AnnouncementResponse implements Serializable {
    private Long id; // 공지사항 id
    private String title; // 공지사항 제목
    private LocalDateTime createdAt; // 공지사항 생성일
}