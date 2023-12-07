package com.team10.backoffice.domain.like.service;

import com.team10.backoffice.domain.like.entity.Like;
import com.team10.backoffice.domain.like.repository.LikeRepository;
import com.team10.backoffice.domain.post.entity.Post;
import com.team10.backoffice.domain.post.repository.PostRepository;
import com.team10.backoffice.domain.users.entity.User;
import com.team10.backoffice.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public Long like(Long postId, Long userId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("게시물이 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("유저가 존재하지 않습니다."));

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);

        if(like.isPresent()) {
            likeRepository.deleteById(like.get().getId());
        } else {
            return likeRepository.save(Like.builder().post(post).user(user).build()).getId();
        }

        return null;
    }
}
