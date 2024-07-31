package com.est.cranejob.recruit.controller;

import com.est.cranejob.post.dto.response.PostSummaryResponse;
import com.est.cranejob.recruit.dto.RecruitInfo;
import com.est.cranejob.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    RecruitService recruitService;

    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }



    @GetMapping
    public String recruit(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword, Model model) {

//        int currentPage = page.orElse(1); //현재 페이지 값이 있으면 리턴 없으면 1
//        int pageSize = size.orElse(9); // 페이지사이즈가 있으면 리턴 없으면 8
//
//        Page<RecruitInfo> recruitList =recruitService.recruitPaged(PageRequest.of(currentPage, pageSize));
//
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("recruitList", recruitList);
//
//        int totalPages = recruitList.getTotalPages();
//        model.addAttribute("totalPages", totalPages);
//
//        //System.out.println(recruitList.getContent());
//
////        if (totalPages > 0){
////            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
////                    .boxed()
////                    .collect(Collectors.toList());
////            model.addAttribute("pageNumbers", pageNumbers);
////        }
//        //페이지 그룹 계산
//        int pageGroupSize = 10;
//        int currentPageGroup = (currentPage - 1) / pageGroupSize + 1;
//        int startPage = (currentPageGroup - 1) * pageGroupSize + 1;
//        int endPage = Math.min(currentPageGroup * pageGroupSize, totalPages);
//
//        List<Integer> pageNumbers = IntStream.rangeClosed(startPage, endPage)
//                .boxed()
//                .collect(Collectors.toList());
//        System.out.println(pageNumbers);
//        model.addAttribute("pageNumbers", pageNumbers);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);



        // 페이징 처리
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);
        String searchKeyword = keyword.orElse("");

        Page<RecruitInfo> recruitList =recruitService.getRecruitPaged(PageRequest.of(currentPage-1, pageSize),searchKeyword);

        model.addAttribute("recruitList", recruitList);

        int totalPages = recruitList.getTotalPages();
        System.out.println(totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }



        return "recruit/recruit-list";
    }


}
