package com.est.cranejob.announcement.service;

import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

	private final UserRepository userRepository;

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
}
