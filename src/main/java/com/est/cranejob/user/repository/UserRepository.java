package com.est.cranejob.user.repository;

import com.est.cranejob.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	@Query("select u from User u where u.username like %?1% or u.nickname like %?1%")
	List<User> findUserByKeyword(String keyword);
}
