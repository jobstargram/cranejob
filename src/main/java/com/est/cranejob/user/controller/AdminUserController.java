package com.est.cranejob.user.controller;

import com.est.cranejob.user.dto.request.CreateAdminUserRequest;
import com.est.cranejob.user.dto.request.UpdateAdminUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.service.AdminUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
	public String adminEditForm(Model model) {
		// 현재 로그인한 사용자 정보 가져오기
		// 왜 UserDetails로 안받고, UserResponse로 받을 수 있는거지?
		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		UserResponse userResponse = adminUserService.findByUsername(username);

		model.addAttribute("updateAdminUserRequest", UpdateAdminUserRequest.toResponseDto(userResponse));
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

	@PutMapping("/admin/edit")
	public String adminEdit(@Valid UpdateAdminUserRequest updateAdminUserRequest, BindingResult bindingResult) {

		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		if (bindingResult.hasErrors()) {
			return "/admin/edit";
		}

		updateAdminUserRequest.setPassword(passwordEncoder.encode(updateAdminUserRequest.getPassword()));
		adminUserService.updateUser(username, updateAdminUserRequest);


		return "redirect:/";
	}

	@GetMapping("/admin/logout")
	public String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/";
	}
}
