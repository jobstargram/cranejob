package com.est.cranejob.user.controller;

import com.est.cranejob.user.dto.CreateUser;
import com.est.cranejob.user.dto.LoginUser;
import com.est.cranejob.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final PasswordEncoder passwordEncoder;
	private final UserService userService;


	@GetMapping("/user/login")
	public String userLoginForm() {
		return "/user/login";
	}

	@PostMapping("/user/login")
	public String userLogin(LoginUser loginUser) {

		return "redirect:/";
	}

	@GetMapping("/user/signup")
	public String userSignUpForm() {
		return "/user/signup";
	}

	@PostMapping("/user/signup")
	public String userSignUp(CreateUser createUser) {
		createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
		userService.createUser(createUser);

		return "redirect:/";
	}

	@GetMapping("/user/edit")
	public String userEditForm() {
		return "/user/edit";
	}
}
