package com.est.cranejob.user.controller;

import com.est.cranejob.user.dto.request.CreateUserRequest;
import com.est.cranejob.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String userLogin() {

		return "redirect:/";
	}

	@GetMapping("/user/signup")
	public String userSignUpForm() {
		return "/user/signup";
	}

	@PostMapping("/user/signup")
	public String userSignUp(CreateUserRequest createUserRequest) {
		createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		userService.createUser(createUserRequest);

		return "redirect:/";
	}

	@GetMapping("/user/edit")
	public String userEditForm() {
		return "/user/edit";
	}

	@GetMapping("/user/logout")
	public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/";
	}

}
