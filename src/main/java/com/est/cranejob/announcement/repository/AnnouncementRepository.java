package com.est.cranejob.announcement.repository;

import com.est.cranejob.announcement.domain.Announcement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
	// 최신날짜 기준으로 공지사항 5개 가져오는 메소드
	List<Announcement> findTop5ByIsDeletedFalseOrderByCreatedAtDesc();
}
