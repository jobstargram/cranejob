package com.est.cranejob.recruit.controller;

import com.est.cranejob.recruit.dto.RecruitInfo;
import com.est.cranejob.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    RecruitService recruitService;

    @GetMapping
    public String recruit(Model model) {
        String apiKey = "OGvfdhi5liDBRnhwW4RoyjDFs1wG6RzZGF8oGPrCSaIGQ1pC";
        String job_mid_cd = "2"; //job_mid_cd
        List<RecruitInfo> Info = recruitService.callApi(apiKey,job_mid_cd);
        model.addAttribute("RecruitInfo",Info);
        return "recruit/recruit-list";
    }

}
