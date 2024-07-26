package com.est.cranejob.user.controller;

import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String adminSignUpForm(Model model) {
		model.addAttribute("createAdminUserRequest", new CreateAdminUserRequest());

		return "/admin/signup";
	}

	@GetMapping("/admin/edit")
	public String adminEditForm() {
		return "/admin/edit";
	}

	@PostMapping("/admin/signup")
	public String adminSignUp(@Valid CreateAdminUserRequest createAdminUserRequest, BindingResult bindingResult) {
		// 아이디 중복 검사
		if (!adminUserService.isDuplicate(createAdminUserRequest.getUsername())) {
			bindingResult.addError(new FieldError("createAdminUserRequest", "username", "이미 존재하는 회원입니다."));
		}

		// 발급 코드 검사
		if (!adminUserService.validateCode(createAdminUserRequest.getCode())) {
			bindingResult.addError(new FieldError("createAdminUserRequest", "code", "코드가 일치하지 않습니다."));
		}

		if (bindingResult.hasErrors()) {
			return "/admin/signup";
		}

		createAdminUserRequest.setPassword(passwordEncoder.encode(createAdminUserRequest.getPassword()));
		adminUserService.createAdminUser(createAdminUserRequest);

		return "/admin/login";
	}

	@PostMapping("/admin/edit")
	public String adminEdit() {
		return "/admin/edit";
	}
}
