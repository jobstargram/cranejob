package com.est.cranejob.announcement.domain;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.util.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Announcement extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Lob
	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	public void setUser(User user) {
		this.user = user;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void delete() {
		this.isDeleted = true;
		this.deletedAt = LocalDateTime.now();
	}
}
