package com.est.cranejob.recruit.dto;

import com.est.cranejob.recruit.domain.Recruit;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitInfo {
    private Long id;
    private String url;
    private String title;
    private String companyName;

    @Override
    public String toString() {
        return "RecruitInfo [url=" + url + ", title=" + title + ", companyName=" + companyName + "]";
    }
//dto를 entity로 변환
    public Recruit toEntity(){
        return new Recruit(this.id,this.url,this.title,this.companyName);
    }
    //Entity를 Dto로 변환
    public static RecruitInfo fromEntity(Recruit recruit){
        return RecruitInfo.builder()
                .id(recruit.getId())
                .url(recruit.getUrl())
                .title(recruit.getTitle())
                .companyName(recruit.getCompanyName())
                .build();

    }
}
