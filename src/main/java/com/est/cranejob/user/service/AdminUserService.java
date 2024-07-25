package com.est.cranejob.user.service;

import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.dto.request.CreateUserRequest;
import com.est.cranejob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {

	private final UserRepository userRepository;

	public void createAdminUser(CreateAdminUserRequest createAdminUserRequest) {
		userRepository.save(createAdminUserRequest.toEntity());
	}

}
