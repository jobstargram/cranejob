package com.est.cranejob.post.controller;

import com.est.cranejob.post.dto.request.CreatePostRequest;
import com.est.cranejob.post.dto.request.UpdatePostRequest;
import com.est.cranejob.post.dto.response.PostSummaryResponse;
import com.est.cranejob.post.dto.response.PostUserDetailResponse;
import com.est.cranejob.post.service.PostService;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPostController {

    private final PostService postService;
    private final UserService userService;

    // 게시글 목록 조회
    @GetMapping("/post/list")
    public String listPosts(Model model) {
        log.debug("Fetching list of posts.");
        List<PostSummaryResponse> postSummaryResponseList = postService.findAllPost();
        model.addAttribute("postSummaryResponseList", postSummaryResponseList);
        return "post/list";
    }

    // 게시글 작성 폼
    @GetMapping("/post/form")
    public String createPostForm(Model model) {
        log.debug("Displaying post creation form.");
        model.addAttribute("createPostRequest", new CreatePostRequest());
        return "post/form";
    }

    // 게시글 작성 요청
    @PostMapping("/post/form")
    public String createPost(@Valid @ModelAttribute CreatePostRequest createPostRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    log.error("Validation error on field {}: {}", error.getField(), error.getDefaultMessage()));
            return "post/form";
        }

        UserResponse userResponse;
        try {
            userResponse = getAuthenticatedUser();
        } catch (UsernameNotFoundException ex) {
            log.error("로그인된 사용자를 찾을 수 없습니다: {}", ex.getMessage());
            return "redirect:/user/login";
        }

        // UserResponse를 User 엔티티로 변환
        User user = userResponse.toEntity();
        postService.createPost(createPostRequest, user);
        log.debug("Post created successfully.");

        return "redirect:/post/list";
    }

    // 현재 인증된 사용자 정보를 가져오는 메서드
    private UserResponse getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 정보가 없거나, 인증되지 않았거나, principal이 null인 경우
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            log.warn("User not authenticated.");
            throw new UsernameNotFoundException("User not authenticated.");
        }

        String username = authentication.getName();
        log.debug("Fetching user by username: {}", username);

        // UserService를 통해 사용자 정보 조회
        return userService.findByUsername(username);
    }



    /* 게시글 상세
    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {
        log.debug("Fetching details for post ID: {}", id);
        PostUserDetailResponse postUserDetailResponse = postService.findPostById(id);
        model.addAttribute("postUserDetailResponse", postUserDetailResponse);
        return "post/userDetail";
    }

    // 게시글 수정 폼
    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        log.debug("Fetching post for edit with ID: {}", id);
        PostUserDetailResponse postUserDetailResponse = postService.findPostById(id);
        model.addAttribute("postUserDetailResponse", postUserDetailResponse);
        model.addAttribute("updatePostRequest", new UpdatePostRequest(
                postUserDetailResponse.getTitle(), postUserDetailResponse.getContent()));
        return "post/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/post/edit/{id}")
    public String updatePost(@PathVariable("id") Long id,
                             @Valid @ModelAttribute UpdatePostRequest updatePostRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return "post/edit";
        }

        log.debug("Updating post ID: {} with data: {}", id, updatePostRequest);
        postService.updatePost(id, updatePostRequest);
        log.debug("Post updated successfully.");

        return "redirect:/post/list";
    }*/
}