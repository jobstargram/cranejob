package com.est.cranejob.announcement.service;

import com.est.cranejob.announcement.domain.Announcement;
import com.est.cranejob.announcement.dto.request.CreateAnnouncementRequest;
import com.est.cranejob.announcement.repository.AnnouncementRepository;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import com.est.cranejob.user.util.Role;
import com.est.cranejob.user.util.UserStatus;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
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
public class AnnouncementService {

	private final UserRepository userRepository;
	private final AnnouncementRepository announcementRepository;

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

	@Transactional
	public void createAnnouncement(CreateAnnouncementRequest createAnnouncementRequest, UserResponse userResponse) {
		User user = userRepository.findByUsername(userResponse.getUsername())
			.orElseThrow(
				() -> new UsernameNotFoundException("No user found with username:" + userResponse.getUsername()));

		Announcement announcement = createAnnouncementRequest.toEntity();
		announcement.setUser(user);

		announcementRepository.save(announcement);
	}

//	@Transactional
//	public void suspendUser(String username) {
//		User user = userRepository.findByUsername(username)
//			.orElseThrow(
//				() -> new UsernameNotFoundException("No user found with username:" + username));
//
//		user.updateStatus(UserStatus.SUSPENDED);
//
//		userRepository.save(user);
//	}
}
