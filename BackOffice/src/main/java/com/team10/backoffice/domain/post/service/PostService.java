package com.team10.backoffice.domain.post.service;

import com.team10.backoffice.domain.post.dto.PostRequestDto;
import com.team10.backoffice.domain.post.dto.PostResponseDto;
import com.team10.backoffice.domain.post.entity.Post;
import com.team10.backoffice.domain.post.repository.PostRepository;
import com.team10.backoffice.domain.users.entity.User;
import com.team10.backoffice.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addPost(PostRequestDto postRequestDTO, User user) {
        Post post = new Post(postRequestDTO, user);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto postRequestDto, User user) {

        Post post = postRepository.findById(id).orElseThrow( // 게시글 존재 여부 검증
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
        if (!post.getUser().getUsername().equals(user.getUsername())){ // 사용자 검증
            throw new IllegalArgumentException("게시글을 작성한 사용자가 아닙니다.");
        }

        post.update(postRequestDto);
    }

    public void removePost(Long id, User user) {

        Post post = postRepository.findById(id).orElseThrow( // 게시글 존재 여부 검증
                () -> new NullPointerException("게시글이 존재하지 않습니다.")
        );
        if (!post.getUser().getUsername().equals(user.getUsername())){ // 사용자 검증
            throw new IllegalArgumentException("게시글을 작성한 사용자가 아닙니다.");
        }

        postRepository.delete(post);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }


    public List<PostResponseDto> getMyPosts(User user) {
        User dbuser = userRepository.findById(user.getId()).orElseThrow(()
                -> new IllegalArgumentException("게시글을 작성한 사용자가 아닙니다.")
        );

        return postRepository.findAllByUser(dbuser).stream().map(PostResponseDto::new).toList();
    }

}
