package com.est.cranejob.user.service;

import com.est.cranejob.user.dto.request.CreateUserRequest;
import com.est.cranejob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void createUser(CreateUserRequest createUserRequest) {
		userRepository.save(createUserRequest.toEntity());
	}

}
