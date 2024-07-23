package com.est.cranejob.user.domain;

import com.est.cranejob.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "user_name")
    private String userName;

    // 비밀번호 15자 초과 x
    @Column(nullable = false, length = 15, name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_status")
    private UserStatus userStatus;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    // 연관 관계
    // 유저의 권한 정보
    @OneToOne(mappedBy = "user")
    private Role role;

    // 회원 탈퇴한 유저
    @OneToOne(mappedBy = "user")
    private DeletedUser deletedUser;

    // 제재 관련 리스트
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPenalty> userPenaltyList;

    // 제재를 가한 관리자 리스트
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<UserPenalty> adminPenaltyList;

    // 게시글 리스트
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Post> postList;
//
//    // 공지 사항 리스트
//    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
//    private List<Announcement> announcementList;
//
//    // 댓글 리스트
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Comment> commentList;
}

