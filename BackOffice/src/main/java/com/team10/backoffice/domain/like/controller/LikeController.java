package com.team10.backoffice.domain.like.controller;

import com.team10.backoffice.domain.like.service.LikeService;
import com.team10.backoffice.etc.response.ApiResponse;
import com.team10.backoffice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likes/{id}")
    public ResponseEntity<ApiResponse> like(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(likeService.like(id, userDetails.getUser().getId()) != null){
            return ResponseEntity.ok(ApiResponse.ok("SUCCESS_ADD_LIKE"));
        }else{
            return ResponseEntity.ok(ApiResponse.ok("SUCCESS_DELETE_LIKE"));
        }
    }

}
