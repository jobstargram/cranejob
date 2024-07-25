package com.est.cranejob.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitInfo {
    private String url;
    private String title;
    private String companyName;

    @Override
    public String toString() {
        return "RecruitInfo [url=" + url + ", title=" + title + ", companyName=" + companyName + "]";
    }
}
