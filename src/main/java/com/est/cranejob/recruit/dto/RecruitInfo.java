package com.est.cranejob.recruit.dto;

import com.est.cranejob.recruit.domain.RecruitApi;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
//RecruitView에 보여줄 API 정보들
public class RecruitInfo {
    private String companyName;
    private String recruitTitle;
    private String recruitUrl;

//    public RecruitApi toCreateRecruit(){
//        return RecruitApi.builder()
//                .companyNameApi(companyName)
//                .recruitTitleApi(recruitTitle)
//                .recruitURLApi(recruitUrl).build();
//    }



}


