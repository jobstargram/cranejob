package com.est.cranejob.announcement.controller;

import com.est.cranejob.announcement.dto.AdminUpdateUserRequest;
import com.est.cranejob.announcement.service.AnnouncementService;
import com.est.cranejob.post.dto.response.PostSummaryResponse;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {

	private final AnnouncementService announcementService;
	private final UserService userService;

	// 공지사항에 대한 CRUD 로직 들어가야함
	@GetMapping("/admin/users")
	public String userList(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword, Model model) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		String searchKeyword = keyword.orElse("");

		Page<UserResponse> userResponses = announcementService.getPaginatedUsers(PageRequest.of(currentPage - 1, pageSize), searchKeyword);

		model.addAttribute("userResponses", userResponses);

		int totalPages = userResponses.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
				.boxed()
				.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "/admin/user-list";
	}

	@GetMapping("/admin/users/{username}")
	public String userDetails(@PathVariable String username, Model model) {

		UserResponse userResponse = announcementService.findUserByUsername(username);

		model.addAttribute("adminUpdateUserRequest", AdminUpdateUserRequest.toResponseDto(userResponse));

		return "/admin/user-info";
	}

	@PostMapping("/admin/users/edit")
	public String updateUserRole(@ModelAttribute AdminUpdateUserRequest adminUpdateUserRequest) {
		announcementService.updateUserRole(adminUpdateUserRequest.getUsername(), adminUpdateUserRequest.getRole(), adminUpdateUserRequest.getUserStatus());

		return "redirect:/admin/users";
	}

//	@PostMapping("/admin/users/suspend/{username}")
//	@ResponseBody
//	public String suspendUser(@PathVariable String username) {
//		announcementService.suspendUser(username);
//
//		return "OK";
//	}

}
