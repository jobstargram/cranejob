package com.est.cranejob.recruit.repository;

import com.est.cranejob.recruit.domain.Recruit;
import com.est.cranejob.recruit.dto.RecruitInfo;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    //Page<RecruitInfo> findByCompanyNameContainingOrTitleContaining(String companyName, String title, Pageable pageable);
//페이징 처리를 위해
    List<Recruit> findAll();
}
