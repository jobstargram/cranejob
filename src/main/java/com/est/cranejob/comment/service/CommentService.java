package com.est.cranejob.comment.service;

import com.est.cranejob.comment.domain.Comment;
import com.est.cranejob.comment.dto.request.CreateCommentRequest;
import com.est.cranejob.comment.dto.response.CommentResponse;
import com.est.cranejob.comment.repository.CommentRepository;
import com.est.cranejob.post.domain.Post;
import com.est.cranejob.post.repository.PostRepository;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createComment(Long postId, User user, CreateCommentRequest createCommentRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(postId + "번 게시글을 찾을 수 없습니다."));

        Comment comment = createCommentRequest.toEntity();
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(commentId + "번 댓글을 찾을 수 없습니다."));
    }

    public Comment updateComment(Long commentId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(commentId + "번 댓글을 찾을 수 없습니다."));

//        comment.setContent(updatedComment.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(commentId + "번 댓글을 찾을 수 없습니다."));

        comment.deletedComment();
        commentRepository.delete(comment);
    }

}
