package com.est.cranejob.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user/login")
	public String userLoginForm() {
		return "/user/login";
	}

	@GetMapping("/user/signup")
	public String userSignUpForm() {
		return "/user/signup";
	}

	@GetMapping("/user/edit")
	public String userEditForm() {
		return "/user/edit";
	}
}
