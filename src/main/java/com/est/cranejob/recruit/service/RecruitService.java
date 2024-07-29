package com.est.cranejob.recruit.service;

import com.est.cranejob.recruit.domain.Recruit;
import com.est.cranejob.recruit.dto.RecruitInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecruitService {

    private final HttpURLConnectionEx httpEx;

    public RecruitService(){
        this.httpEx = new HttpURLConnectionEx();
    }

    public List<RecruitInfo> callApi(String apiKey, String job_mid_cd, String count){
        List<RecruitInfo> recruitInfoList = new ArrayList<>();

        String requestUrl = "https://oapi.saramin.co.kr/job-search?access-key=" + apiKey + "&job_mid_cd=" + job_mid_cd + "&count=" + count;

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        try {

            String response = httpEx.get(requestUrl, headers);
            Gson gson = new Gson();
            //스트링을 JSON으로 바꾼다
            Map<String, Object> map = gson.fromJson(response, Map.class);
            Map<String, Object> map2 = (Map<String, Object>) map.get("jobs");
            ArrayList<Object> arr = (ArrayList<Object>) map2.get("job");


            for (Object o : arr) {

                Map<String, Object> map3 = (Map<String, Object>) o;
                RecruitInfo recruitInfoTemp = new RecruitInfo();
                recruitInfoTemp.setUrl(String.valueOf(map3.get("url")));

                recruitInfoTemp.setCompanyName(String.valueOf((String)((Map<String,Object>)((Map<String,Object>)map3.get("company")).get("detail")).get("name")
                ));
                recruitInfoTemp.setTitle(String.valueOf((String)((Map<String,Object>)map3.get("position")).get("title")));
                //System.out.println(recruitInfoTemp.toString());
                recruitInfoList.add(recruitInfoTemp);

            }

            return recruitInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

