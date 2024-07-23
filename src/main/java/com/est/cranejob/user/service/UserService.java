package com.est.cranejob.user.service;

import com.est.cranejob.user.dto.CreateUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void createUser(CreateUser createUser) {
		System.out.println(createUser.getName());
	}

}
