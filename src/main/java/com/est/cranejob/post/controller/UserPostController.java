package com.est.cranejob.post.controller;

import com.est.cranejob.post.dto.request.CreatePostRequest;
import com.est.cranejob.post.dto.request.UpdatePostRequest;
import com.est.cranejob.post.dto.response.PostSummaryResponse;
import com.est.cranejob.post.dto.response.PostUserDetailResponse;
import com.est.cranejob.post.service.PostService;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import com.est.cranejob.user.service.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPostController {

    private final PostService postService;
    private final UserRepository userRepository;

    // 게시글 목록 조회
    @GetMapping("/post/list")
    public String listPosts(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword, Model model) {
        log.debug("Fetching list of posts.");

        // 페이징 처리
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        String searchKeyword = keyword.orElse("");

        Page<PostSummaryResponse> postSummaryResponseList = postService.getPaginatedPosts(PageRequest.of(currentPage - 1, pageSize), searchKeyword);

        model.addAttribute("postSummaryResponseList", postSummaryResponseList);

        int totalPages = postSummaryResponseList.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

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
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return "post/form"; // 오류가 있을 경우 원래 폼으로 돌아가게 설정
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            log.warn("User not authenticated. Redirecting to login page.");
            return "redirect:/user/login";  // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
        }

        UserResponse principal = (UserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("Creating post for user: {}", principal.getUsername());

        User user;
        try {
            user = userRepository.findByUsername( principal.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " +  principal.getUsername()));
            log.debug("User found: {}", user);
        } catch (UsernameNotFoundException ex) {
            log.error("로그인된 사용자를 찾을 수 없습니다: {}", ex.getMessage());
            return "redirect:/user/login";
        }

        postService.createPost(createPostRequest, user);
        log.debug("Post created successfully.");

        return "redirect:/post/list";
    }

    // 게시글 상세
    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {
        log.debug("Fetching details for post ID: {}", id);
        PostUserDetailResponse postUserDetailResponse = postService.findPostById(id);
        model.addAttribute("postUserDetailResponse", postUserDetailResponse);
        return "post/userDetail";
    }

    // 게시글 수정 폼
    @GetMapping("/post/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        log.debug("Fetching post for edit with ID: {}", id);
        PostUserDetailResponse postUserDetailResponse = postService.findPostById(id);
        model.addAttribute("postUserDetailResponse", postUserDetailResponse);
        model.addAttribute("updatePostRequest", new UpdatePostRequest(
                postUserDetailResponse.getTitle(), postUserDetailResponse.getContent()));
        // 현재 로그인한 사용자 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            log.warn("User not authenticated. Redirecting to login page.");
            return "redirect:/user/login";
        }

        UserResponse principal = (UserResponse) authentication.getPrincipal();
        String currentUsername = principal.getUsername();

        if (!postUserDetailResponse.getUsername().equals(currentUsername)) {
            log.error("User is not the author of the post. Access denied.");
            return "redirect:/post/list"; // 또는 에러 페이지로 리다이렉트
        }

        return "post/edit";
    }

    // 게시글 수정 처리
    @PutMapping("/post/edit/{id}")
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
    }

}