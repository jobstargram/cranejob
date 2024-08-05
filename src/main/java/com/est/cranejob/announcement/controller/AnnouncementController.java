package com.est.cranejob.announcement.controller;

import com.est.cranejob.announcement.dto.request.CreateAnnouncementRequest;
import com.est.cranejob.announcement.dto.request.UpdateAnnouncementRequest;
import com.est.cranejob.announcement.dto.response.AnnouncementResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {

	private final AnnouncementService announcementService;

	@GetMapping("/announcements")
	public String getAnnouncements(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword, Model model) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);
		String searchKeyword = keyword.orElse("");

		Page<AnnouncementResponse> announcementResponses = announcementService.getPaginatedUsers(
			PageRequest.of(currentPage - 1, pageSize), searchKeyword);

		model.addAttribute("announcementResponses", announcementResponses);

		int totalPages = announcementResponses.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
				.boxed()
				.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "announcement/list";
	}

	@PostMapping("/announcements")
	public String createAnnouncement(@Valid CreateAnnouncementRequest createAnnouncementRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "announcement/form";
		}

		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		announcementService.createAnnouncement(createAnnouncementRequest, principal);

		return "redirect:/";
	}


	@GetMapping("/announcements/form")
	public String createAnnouncementForm(Model model) {
		model.addAttribute("createAnnouncementRequest", new CreateAnnouncementRequest());

		return "announcement/form";
	}

	@GetMapping("/announcements/{id}")
	public String announcementDetail(@PathVariable Long id, Model model) {
		AnnouncementResponse announcementResponse = announcementService.findAnnouncementById(id);
		model.addAttribute("announcementResponse", announcementResponse);

		return "announcement/detail";
	}

	@GetMapping("/announcements/edit/{id}")
	public String updateAnnouncementForm(@PathVariable Long id, Model model) {
		UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();

		UserResponse announcementUser = announcementService.findAnnouncementWriterById(id);

		if (!announcementUser.getUsername().equals(username)) {
			return "post/list";
		}

		AnnouncementResponse announcementResponse = announcementService.findAnnouncementById(id);
		model.addAttribute("updateAnnouncementRequest", UpdateAnnouncementRequest.toResponseDto(announcementResponse));

		return "announcement/edit";
	}

	@PatchMapping("/announcements/edit/{id}")
	public String updateAnnouncement(@PathVariable Long id, @Valid UpdateAnnouncementRequest updateAnnouncementRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "announcement/form";
		}

		announcementService.updateAnnouncement(id, updateAnnouncementRequest.getTitle(), updateAnnouncementRequest.getContent());

		return "redirect:/announcements";
	}

	@DeleteMapping("/announcements/{id}")
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {

		announcementService.deleteAnnouncement(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}




}
