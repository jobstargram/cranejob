package com.est.cranejob.post.domain;

import com.est.cranejob.comment.domain.Comment;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	// 연관관계 편의 메소드
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setPost(this);
	}

	// 게시글 업데이트
	public void updatePost(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public void deletedPost(){
		this.isDeleted = true;
		this.deletedAt = LocalDateTime.now();
	}

	public void setUser(User user) {
		this.user = user;
	}
}