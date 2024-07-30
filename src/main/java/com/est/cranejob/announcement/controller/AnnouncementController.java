package com.est.cranejob.announcement.controller;

import com.est.cranejob.announcement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {

	private final AnnouncementService announcementService;

	// 공지사항에 대한 CRUD 로직 들어가야함

}
