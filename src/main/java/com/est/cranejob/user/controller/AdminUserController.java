package com.est.cranejob.user.controller;

import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

	private final PasswordEncoder passwordEncoder;
	private final AdminUserService adminUserService;

	@GetMapping("/admin/login")
	public String adminLoginForm() {
		return "/admin/login";
	}

	@GetMapping("/admin/signup")
	public String adminSignUpForm() {
		return "/admin/signup";
	}

	@GetMapping("/admin/edit")
	public String adminEditForm() {
		return "/admin/edit";
	}


	@PostMapping("/admin/login")
	public String adminLogin() {
		return "redirect:/";
	}

	@PostMapping("/admin/signup")
	public String adminSignUp(CreateAdminUserRequest createAdminUserRequest) {
		createAdminUserRequest.setPassword(passwordEncoder.encode(createAdminUserRequest.getPassword()));
		adminUserService.createAdminUser(createAdminUserRequest);

		return "/admin/login";
	}

	@PostMapping("/admin/edit")
	public String adminEdit() {
		return "/admin/edit";
	}
}
