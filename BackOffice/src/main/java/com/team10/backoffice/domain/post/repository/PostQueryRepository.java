package com.team10.backoffice.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.backoffice.domain.post.entity.Post;
import com.team10.backoffice.domain.post.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List< Post > findOrderByContentLengthDesc() {
		QPost post = new QPost( "post" );
		return jpaQueryFactory.selectFrom( post ).orderBy( post.content.length().desc() ).fetch();
	}
}
