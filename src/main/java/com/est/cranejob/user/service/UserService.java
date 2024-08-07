package com.est.cranejob.user.service;

import com.est.cranejob.comment.service.CommentService;
import com.est.cranejob.post.service.PostService;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.request.CreateUserRequest;
import com.est.cranejob.user.dto.request.UpdateUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PostService postService;
	private final CommentService commentService;

	@Transactional
	public void createUser(CreateUserRequest createUserRequest) {
		userRepository.save(createUserRequest.toEntity());
	}

	@Transactional
	public void updateUser(String username, UpdateUserRequest updateUserRequest) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user found with username:" + username));

		user.updateUser(updateUserRequest.getPassword(), updateUserRequest.getNickname());

		userRepository.save(user);
	}

	public UserResponse findByUsername(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user found with username:" + username));

		return UserResponse.toDto(user);
	}

	public boolean isDuplicate(String username) {
		Optional<User> user = userRepository.findByUsername(username);

		return user.isEmpty();
	}

	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user found with username:" + username));

		user.deleteUser();

		userRepository.save(user);
		// 상태가 DELETED로 변경된 경우 해당 사용자의 모든 게시글을 논리 삭제
		postService.deletePostsByUser(user);
		commentService.deleteCommentByUser(user);
	}
}
