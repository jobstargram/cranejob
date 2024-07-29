package com.est.cranejob.recruit.controller;

import com.est.cranejob.recruit.dto.RecruitInfo;
import com.est.cranejob.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    RecruitService recruitService;

    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }



    @GetMapping
    public String recruit(Model model) {

        //model.addAttribute("RecruitInfo",info);
        return "recruit/recruit-list";
    }

}
