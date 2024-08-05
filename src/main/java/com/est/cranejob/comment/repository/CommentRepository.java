package com.est.cranejob.comment.repository;

import com.est.cranejob.comment.domain.Comment;
import com.est.cranejob.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndIsDeletedFalse(Long postId); // 수정된 부분
    List<Comment> findAllByUser(User user);
}
