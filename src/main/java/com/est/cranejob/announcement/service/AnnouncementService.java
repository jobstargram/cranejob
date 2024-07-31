package com.est.cranejob.announcement.service;

import com.est.cranejob.announcement.domain.Announcement;
import com.est.cranejob.announcement.dto.request.CreateAnnouncementRequest;
import com.est.cranejob.announcement.dto.response.AnnouncementDetailResponse;
import com.est.cranejob.announcement.dto.response.AnnouncementResponse;
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

	@Transactional
	public void createAnnouncement(CreateAnnouncementRequest createAnnouncementRequest, UserResponse userResponse) {
		User user = userRepository.findByUsername(userResponse.getUsername())
			.orElseThrow(
				() -> new UsernameNotFoundException("No user found with username:" + userResponse.getUsername()));

		Announcement announcement = createAnnouncementRequest.toEntity();
		announcement.setUser(user);

		announcementRepository.save(announcement);
	}

	@Transactional
	public void updateAnnouncement(Long id, String title, String content) {
		Announcement announcement = announcementRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

		announcement.update(title, content);

		announcementRepository.save(announcement);
	}

	public UserResponse findAnnouncementWriterById(Long id) {
		Announcement announcement = announcementRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

		return UserResponse.toDto(announcement.getUser());
	}

	public AnnouncementDetailResponse findAnnouncementById(Long id) {
		Announcement announcement = announcementRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

		return AnnouncementDetailResponse.toDTO(announcement);
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
