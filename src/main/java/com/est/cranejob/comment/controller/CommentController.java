package com.est.cranejob.comment.controller;

import com.est.cranejob.comment.domain.Comment;
import com.est.cranejob.comment.dto.request.CreateCommentRequest;
import com.est.cranejob.comment.dto.request.UpdateCommentRequest;
import com.est.cranejob.comment.dto.response.CommentResponse;
import com.est.cranejob.comment.service.CommentService;
import com.est.cranejob.post.service.PostService;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.query.sqm.tree.SqmNode.log;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final UserRepository userRepository;

    // 댓글 목록 가져오기
    @GetMapping("/comment/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("postId") Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        List<CommentResponse> commentResponses = comments.stream()
                .map(CommentResponse::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentResponses);
    }

    // 댓글 추가
    @PostMapping("/comment/{postId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable("postId") Long postId,
                                                      @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.warn("Authentication object is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!authentication.isAuthenticated()) {
            log.warn("User is not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (authentication.getPrincipal() == null) {
            log.warn("Principal is not an instance of UserDetails");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Comment comment = commentService.createComment(postId, user, createCommentRequest);
        return ResponseEntity.ok(CommentResponse.toDTO(comment));
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") Long commentId,
                                                         @Valid @RequestBody UpdateCommentRequest updateRequest,
                                                         Model model) {
        // 현재 로그인한 사용자 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.warn("Authentication object is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!authentication.isAuthenticated()) {
            log.warn("User is not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (authentication.getPrincipal() == null) {
            log.warn("Principal is not an instance of UserDetails");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Comment comment = commentService.getComment(commentId);
        if (!comment.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 수정 권한이 없는 경우
        }

//        model.addAttribute("");

        comment.updateComment(updateRequest.getContent());
        Comment updatedComment = commentService.updateComment(commentId, comment);
        return ResponseEntity.ok(CommentResponse.toDTO(updatedComment));
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        // 현재 로그인한 사용자 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.warn("Authentication object is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!authentication.isAuthenticated()) {
            log.warn("User is not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (authentication.getPrincipal() == null) {
            log.warn("Principal is not an instance of UserDetails");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Comment comment = commentService.getComment(commentId);
        if (!comment.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 삭제 권한이 없는 경우
        }

        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
