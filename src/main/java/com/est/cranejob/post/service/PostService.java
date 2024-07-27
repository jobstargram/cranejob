package com.est.cranejob.post.service;

import com.est.cranejob.post.domain.Post;
import com.est.cranejob.post.dto.request.CreatePostRequest;
import com.est.cranejob.post.dto.request.UpdatePostRequest;
import com.est.cranejob.post.dto.response.PostSummaryResponse;
import com.est.cranejob.post.dto.response.PostUserDetailResponse;
import com.est.cranejob.post.repository.PostRepository;
import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    // username(아이디)과 CreatePostRequest에서 작성한 내용을 받아서 Post 엔티티 생성 및 저장
    @Transactional
    public void createPost(CreatePostRequest createPostRequest, User user){
        log.debug("Creating post with title: {} and content: {}", createPostRequest.getTitle(), createPostRequest.getContent());
        Post post = createPostRequest.toEntity();
        post.setUser(user);
        postRepository.save(post);
        log.debug("Post created and saved successfully.");
    }

    // 모든 게시글을 조회해서 PostSummaryResponse 리스트로 반환
    @Transactional(readOnly = true)
    public List<PostSummaryResponse> findAllPost(){
        log.debug("Fetching all posts.");
        return postRepository.findAll().stream()
                .map(PostSummaryResponse::toDTO)
                .toList();
    }

    /*// 사용자용 게시글 상세 정보를 조회하여 PostUserDetailResponse로 반환
    @Transactional(readOnly = true)
    public PostUserDetailResponse findPostById(Long id){
        log.debug("Update post details for ID: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        return PostUserDetailResponse.toDTO(post);
    }

    // 게시글 업데이트 후 저장
    @Transactional
    public void updatePost(Long id, UpdatePostRequest updatePostRequest){
        log.debug("Updating post ID: {} with data: {}", id, updatePostRequest);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        post.updatePost(updatePostRequest.getTitle(), updatePostRequest.getContent());
        postRepository.save(post);
        log.debug("Post updated and saved successfully.");
    }*/

}
