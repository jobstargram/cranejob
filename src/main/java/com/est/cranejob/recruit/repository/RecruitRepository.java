package com.est.cranejob.recruit.repository;


import com.est.cranejob.recruit.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    @Query(value = "SELECT * FROM recruit_info WHERE title LIKE %:keyword% OR company_name LIKE %:keyword%", nativeQuery = true)
    List<Recruit> findByTitleOrCompanyName(@Param("keyword") String keyword);
    List<Recruit> findAll();


}
