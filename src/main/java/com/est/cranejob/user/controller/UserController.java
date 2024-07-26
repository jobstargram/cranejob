package com.est.cranejob.user.controller;

import com.est.cranejob.user.dto.request.CreateUserRequest;
import com.est.cranejob.user.dto.request.UpdateUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.service.UserService;
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
public class UserController {

	private final PasswordEncoder passwordEncoder;
	private final UserService userService;


	@GetMapping("/user/login")
	public String userLoginForm() {
		return "/user/login";
	}

	@GetMapping("/user/signup")
	public String userSignUpForm(Model model) {
		model.addAttribute("createUserRequest", new CreateUserRequest());

		return "/user/signup";
	}

	@GetMapping("/user/edit")
	public String userEditForm(Model model) {
		// 현재 로그인한 사용자 정보 가져오기
		// 왜 UserDetails로 안받고, UserResponse로 받을 수 있는거지?
		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		UserResponse userResponse = userService.findByUsername(username);

		model.addAttribute("updateUserRequest", UpdateUserRequest.toResponseDto(userResponse));

		return "/user/edit";
	}


	@PostMapping("/user/signup")
	public String userSignUp(@Valid CreateUserRequest createUserRequest, BindingResult bindingResult) {
		// 아이디 중복 검사
		if (!userService.isDuplicate(createUserRequest.getUsername())) {
			bindingResult.addError(new FieldError("createUserRequest", "username", "이미 존재하는 회원입니다."));
			return "/user/signup";
		}

		if (bindingResult.hasErrors()) {
			return "/user/signup";
		}

		createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		userService.createUser(createUserRequest);

		return "redirect:/";
	}

	@PutMapping("/user/edit")
	public String userEdit(@Valid UpdateUserRequest updateUserRequest, BindingResult bindingResult) {
		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		if (bindingResult.hasErrors()) {
			return "/user/edit";
		}

		updateUserRequest.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
		userService.updateUser(username, updateUserRequest);

		return "redirect:/";
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
