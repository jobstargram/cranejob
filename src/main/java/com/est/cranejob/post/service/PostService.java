package com.est.cranejob.post.service;

import com.est.cranejob.announcement.domain.Announcement;
import com.est.cranejob.announcement.repository.AnnouncementRepository;
import com.est.cranejob.comment.domain.Comment;
import com.est.cranejob.comment.service.CommentService;
import com.est.cranejob.post.domain.Post;
import com.est.cranejob.post.dto.request.CreatePostRequest;
import com.est.cranejob.post.dto.request.UpdatePostRequest;
import com.est.cranejob.post.dto.response.PostSummaryResponse;
import com.est.cranejob.post.dto.response.PostUserDetailResponse;
import com.est.cranejob.post.repository.PostRepository;
import com.est.cranejob.user.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AnnouncementRepository announcementRepository;
    private final CommentService commentService;


    // username(아이디)과 CreatePostRequest에서 작성한 내용을 받아서 Post 엔티티 생성 및 저장
    @Transactional
    public void createPost(CreatePostRequest createPostRequest, User user) {
        log.debug("Creating post with title: {} and content: {}", createPostRequest.getTitle(), createPostRequest.getContent());
        Post post = createPostRequest.toEntity();
        post.setUser(user);
        postRepository.save(post);
        log.debug("Post created and saved successfully.");
    }

    // 모든 게시글을 조회해서 PostSummaryResponse 리스트로 반환
    @Transactional(readOnly = true)
    public List<PostSummaryResponse> findAllPost() {
        log.debug("Fetching all posts.");
        return postRepository.findAll().stream()
                .map(PostSummaryResponse::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostUserDetailResponse findPostById(Long id) {
        log.debug("Update post details for ID: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        List<Comment> comments = commentService.getCommentsByPostId(id);
        return PostUserDetailResponse.toDTO(post, comments);
    }

    // 게시글 업데이트 후 저장
    @Transactional
    public void updatePost(Long id, UpdatePostRequest updatePostRequest) {
        log.debug("Updating post ID: {} with data: {}", id, updatePostRequest);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        post.updatePost(updatePostRequest.getTitle(), updatePostRequest.getContent());
        postRepository.save(post);
        log.debug("Post updated and saved successfully.");
    }

    public Page<PostSummaryResponse> getPaginatedPosts(Pageable pageable, String keyword) {
        // 페이지네이션 기본 정보 설정
        int pageSize = pageable.getPageSize(); // 한 페이지당 항목 수
        int currentPage = pageable.getPageNumber(); // 현재 페이지 번호 (0부터 시작)
        int startItem = currentPage * pageSize; // 현재 페이지의 시작 항목 인덱스

        // 최근 5개의 공지사항을 가져옴
        List<Announcement> announcements = announcementRepository.findTop5ByIsDeletedFalseOrderByCreatedAtDesc();
        List<PostSummaryResponse> announcementList = announcements.stream()
                .map(PostSummaryResponse::fromAnnouncement)
                .collect(Collectors.toList());

        // 키워드로 일반 게시글을 검색하고, 페이지네이션을 적용
        List<Post> posts = postRepository.findPostByKeyword(keyword);
        List<PostSummaryResponse> postList = posts.stream()
                .map(PostSummaryResponse::toDTO)
                .collect(Collectors.toList());

        // 일반 게시글의 시작 인덱스를 조정 (공지사항을 제외한 인덱스)
        int postStartIndex = Math.max(0, startItem - announcementList.size());
        int postEndIndex = Math.min(postStartIndex + pageSize - announcementList.size(), postList.size());

        // 공지사항과 일반 게시글을 합침
        List<PostSummaryResponse> combinedList = new ArrayList<>();
        combinedList.addAll(announcementList);

        if (postStartIndex < postList.size()) {
            combinedList.addAll(postList.subList(postStartIndex, postEndIndex));
        }

        // 페이지네이션 결과를 PageImpl 객체로 반환
        return new PageImpl<>(combinedList, pageable, announcementList.size() + postList.size());
    }

    public void deletePost(Long id, User requestingUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번의 게시글을 찾을 수 없습니다"));

        if (!post.getUser().equals(requestingUser)) {
            throw new SecurityException("삭제 권한이 없는 사용자 입니다.");
        }

        post.deletedPost();
        postRepository.save(post);
    }

    @Transactional
    public void deletePostsByUser(User user) {
        List<Post> posts = postRepository.findAllByUser(user);
        for (Post post : posts) {
            post.deletedPost();
        }
        postRepository.saveAll(posts); // bulk update
    }
}
