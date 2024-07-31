package com.est.cranejob.recruit.service;

import com.est.cranejob.recruit.domain.Recruit;
import com.est.cranejob.recruit.dto.RecruitInfo;
import com.est.cranejob.recruit.repository.RecruitRepository;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;



@Service
public class RecruitService {

    private final HttpURLConnectionEx httpEx;
    private final RecruitRepository recruitRepository;

    //api 호출 관련 로직
    public RecruitService(RecruitRepository recruitRepository){
        this.httpEx = new HttpURLConnectionEx();
        this.recruitRepository = recruitRepository;
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

    public Page<RecruitInfo> getRecruitPaged(Pageable pageable, String keyword){

        int pageSize = pageable.getPageSize(); //한페이지의 사이즈
        int currentPage = pageable.getPageNumber(); // 현재 페이지 넘버
        int startIndex = currentPage * pageSize; // 아이템의 페이지 시작 인덱스

        List<RecruitInfo> recruitList = null;
        //검색어가 없을때
        if("".equals(keyword)){
            recruitList = recruitRepository.findAll().stream()
                    .map(RecruitInfo::fromEntity)
                    .collect(Collectors.toList());

        }else{//검색어가 있을때 모두 출력
            recruitList = recruitRepository.findByTitleOrCompanyName(keyword).stream()
                    .map(RecruitInfo::fromEntity)
                    .collect(Collectors.toList());
        }



        List<RecruitInfo> recruitPageList;

        if (recruitList.size() < startIndex){
            recruitPageList = Collections.emptyList();
        }else{
            int toIndex = Math.min(startIndex + pageSize, recruitList.size()); //마지막 페이지 조심
            recruitPageList = recruitList.subList(startIndex, toIndex);
        }

        return new PageImpl<>(recruitPageList,  PageRequest.of(currentPage, pageSize), recruitList.size());

    }
}

