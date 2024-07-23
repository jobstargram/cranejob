package com.est.cranejob.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

	@PostMapping("/api/login")
	public String index() {
		return "Hello";
	}

}
