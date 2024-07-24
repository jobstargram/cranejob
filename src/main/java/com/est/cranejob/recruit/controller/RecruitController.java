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
//로그아웃 버튼 클릭시
    @GetMapping("/user/logout")
    public String logout(Model model) {
        return "user/login";
    }
//내 정보 수정
    @GetMapping("/user/userInfo")
    public String userInfo(Model model) {
        return "user/user-info";
    }



}
