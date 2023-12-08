package com.team10.backoffice.domain.post.controller;

import com.team10.backoffice.domain.post.dto.PostRequestDto;
import com.team10.backoffice.domain.post.dto.PostResponseDto;
import com.team10.backoffice.domain.post.entity.Post;
import com.team10.backoffice.domain.post.service.PostService;
import com.team10.backoffice.etc.response.ApiResponse;
import com.team10.backoffice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/posts") // 게시글 등록
    public ResponseEntity<ApiResponse> addPost(@RequestBody PostRequestDto postRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.addPost(postRequestDTO, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.ok("SUCCESS_ADD_POST"));
    }

    @PatchMapping("/posts/{id}") // 게시글 수정
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(id, postRequestDto, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.ok("SUCCESS_UPDATE_POST"));
    }

    @DeleteMapping("/posts/{id}") // 게시글 삭제
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.removePost(id, userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.ok("SUCCESS_DELETE_POST"));
    }

    @GetMapping("/posts") // 게시글 전체 조회
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/myposts") // 내가 작성한 게시글
    public List<PostResponseDto> getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getMyPosts(userDetails.getUser());
    }
}
