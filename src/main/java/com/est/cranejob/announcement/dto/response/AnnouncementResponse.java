package com.est.cranejob.announcement.dto.response;

import com.est.cranejob.announcement.domain.Announcement;
import com.est.cranejob.user.domain.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 공지사항 요약을 나타내는 DTO<br>
 * DTO for {@link com.est.cranejob.announcement.domain.Announcement}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementResponse implements Serializable {

    private Long id;
    private String title;
    private String content;
    private User user;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public static AnnouncementResponse toDTO(Announcement announcement){
        return AnnouncementResponse.builder()
            .id(announcement.getId())
            .title(announcement.getTitle())
            .content(announcement.getContent())
            .user(announcement.getUser())
            .isDeleted(announcement.isDeleted())
            .createdAt(announcement.getCreatedAt())
            .updatedAt(announcement.getUpdatedAt())
            .deletedAt(announcement.getDeletedAt())
            .build();
    }
}