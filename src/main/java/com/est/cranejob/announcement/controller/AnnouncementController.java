package com.est.cranejob.announcement.controller;

import com.est.cranejob.announcement.dto.AdminUpdateUserRequest;
import com.est.cranejob.announcement.dto.request.CreateAnnouncementRequest;
import com.est.cranejob.announcement.dto.request.UpdateAnnouncementRequest;
import com.est.cranejob.announcement.dto.response.AnnouncementDetailResponse;
import com.est.cranejob.announcement.service.AnnouncementService;
import com.est.cranejob.user.dto.response.UserResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {

	private final AnnouncementService announcementService;

	@GetMapping("/announcement/form")
	public String createAnnouncementForm(Model model) {
		model.addAttribute("createAnnouncementRequest", new CreateAnnouncementRequest());

		return "/announcement/form";
	}

	@GetMapping("/announcement/edit/{id}")
	public String updateAnnouncementForm(@PathVariable Long id, Model model) {
		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		UserResponse announcementUser = announcementService.findAnnouncementWriterById(id);

		if (!announcementUser.getUsername().equals(username)) {
			return "/post/list";
		}

		AnnouncementDetailResponse announcementDetailResponse = announcementService.findAnnouncementById(id);
		model.addAttribute("updateAnnouncementRequest", UpdateAnnouncementRequest.toResponseDto(announcementDetailResponse));

		return "/announcement/edit";
	}

	@PatchMapping("/announcement/edit/{id}")
	public String updateAnnouncement(@PathVariable Long id, @Valid UpdateAnnouncementRequest updateAnnouncementRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/announcement/form";
		}

		announcementService.updateAnnouncement(id, updateAnnouncementRequest.getTitle(), updateAnnouncementRequest.getContent());


		return "/announcement/edit";
	}

	@PostMapping("/announcement/form")
	public String createAnnouncement(@Valid CreateAnnouncementRequest createAnnouncementRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/announcement/form";
		}

		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		announcementService.createAnnouncement(createAnnouncementRequest, principal);

		return "redirect:/";
	}



}
