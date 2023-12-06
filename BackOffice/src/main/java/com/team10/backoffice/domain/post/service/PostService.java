package com.team10.backoffice.domain.post.service;

import com.team10.backoffice.domain.post.dto.PostRequestDto;
import com.team10.backoffice.domain.post.entity.Post;
import com.team10.backoffice.domain.post.repository.PostRepository;
import com.team10.backoffice.domain.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.DuplicateFormatFlagsException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void addPost(PostRequestDto postRequestDTO) {
        Post post = new Post(postRequestDTO);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto postRequestDto, User user) {

//        Post post = postRepository.findById(id).orElseThrow( // 게시글 존재 여부 검증
//                () ->
//
//        if (!post.getUser().getUsername().equals(user.getUsername())){ // 사용자 검증
//            throw new
//        }
//
//        post.update(postRequestDto);
    }

    public void removePost(Long id, User user) {

//        Post post = postRepository.findById(id).orElseThrow( // 게시글 존재 여부 검증
//                () ->
//
//        if (!post.getUser().getUsername().equals(user.getUsername())){ // 사용자 검증
//            throw new
//        }

//        postRepository.delete(post);
    }
}
