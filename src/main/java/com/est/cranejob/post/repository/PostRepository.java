package com.est.cranejob.post.repository;

import com.est.cranejob.post.domain.Post;
import java.util.List;

import com.est.cranejob.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("select p from Post p where (p.title like %?1% or p.user.nickname like %?1%) and p.isDeleted = false")
	List<Post> findPostByKeyword(String keyword);

    List<Post> findAllByUser(User user);
}
