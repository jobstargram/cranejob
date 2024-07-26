package com.est.cranejob.user.service;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.dto.request.UpdateAdminUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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



}
