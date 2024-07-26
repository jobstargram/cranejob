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
    @Autowired
    RecruitService recruitService;

    @Value("${SARAMIN.ApiKey}")
    String apiKey;
    @Value("${SARAMIN.Job_Mid_Cd}")
    String job_mid_cd; //job_mid_cd

    @GetMapping
    public String recruit(Model model) {

        List<RecruitInfo> info = recruitService.callApi(apiKey,job_mid_cd);
        System.out.println(info.get(0));
        System.out.println(info.get(1));
        System.out.println(info.get(2));
        model.addAttribute("RecruitInfo",info);
        return "recruit/recruit-list";
    }

}
