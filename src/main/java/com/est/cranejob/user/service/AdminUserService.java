package com.est.cranejob.user.service;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.dto.request.UpdateAdminUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import com.est.cranejob.user.util.Role;
import com.est.cranejob.user.util.UserStatus;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {

	private static final String ADMIN_CODE = "CraneJob";

	private final UserRepository userRepository;

	@Transactional
	public void createAdminUser(CreateAdminUserRequest createAdminUserRequest) {
		userRepository.save(createAdminUserRequest.toEntity());
	}

	@Transactional
	public void updateUser(String username, UpdateAdminUserRequest updateAdminUserRequest) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user found with username:" + username));

		user.updateUser(updateAdminUserRequest.getPassword(), updateAdminUserRequest.getNickname());

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

	public boolean validateCode(String code) {
		return code.equals(ADMIN_CODE);
	}

	public Page<UserResponse> getPaginatedUsers(Pageable pageable, String keyword) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<UserResponse> userList = userRepository.findUserByKeyword(keyword).stream()
			.map(UserResponse::toDto)
			.collect(Collectors.toList());


		List<UserResponse> userPageList;

		if (userList.size() < startItem) {
			userPageList = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, userList.size());
			userPageList = userList.subList(startItem, toIndex);
		}

		return new PageImpl<>(userPageList, PageRequest.of(currentPage, pageSize), userList.size());
	}

	public UserResponse findUserByUsername(String username) {
		return userRepository.findByUsername(username)
			.map(UserResponse::toDto) // User가 존재할 경우 UserResponse로 변환
			.orElse(new UserResponse()); // User가 존재하지 않을 경우 빈 UserResponse 반환
	}

	@Transactional
	public void updateUserRole(String username, Role role, UserStatus userStatus) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(
				() -> new UsernameNotFoundException("No user found with username:" + username));

		user.updateRoleAndStatus(role, userStatus);

		userRepository.save(user);
	}


}
