package com.est.cranejob.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    @GetMapping
    public String recruit(Model model) {
        return "recruit/recruit-list";
    }

}
