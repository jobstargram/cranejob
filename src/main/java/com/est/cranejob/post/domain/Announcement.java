package com.est.cranejob.post.domain;

import com.est.cranejob.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long announcementId;

    // 공지 사항 작성자
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @Column(name = "announce_title")
    private String title;

    @Lob
    @Column(name = "announce_content")
    private String content;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;
}