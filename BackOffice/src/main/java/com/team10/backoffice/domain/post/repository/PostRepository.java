package com.team10.backoffice.domain.post.repository;

import com.team10.backoffice.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
