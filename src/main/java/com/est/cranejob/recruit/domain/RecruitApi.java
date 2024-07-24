package com.est.cranejob.recruit.domain;

import com.est.cranejob.recruit.dto.RecruitInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
//API 정보들을 담아둔다.
public class RecruitApi {
    private String companyNameApi;
    private String recruitTitleApi;
    private String recruitURLApi;


    public RecruitInfo toRecruitInfo(){
        return RecruitInfo.builder()
                .companyName(companyNameApi)
                .recruitTitle(recruitTitleApi)
                .recruitUrl(recruitURLApi).build();
    }



}
