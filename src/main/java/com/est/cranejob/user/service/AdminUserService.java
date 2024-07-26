package com.est.cranejob.user.service;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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

	public boolean isDuplicate(String username) {
		Optional<User> user = userRepository.findByUsername(username);

		return user.isEmpty();
	}

	public boolean validateCode(String code) {
		return code.equals(ADMIN_CODE);
	}
}
