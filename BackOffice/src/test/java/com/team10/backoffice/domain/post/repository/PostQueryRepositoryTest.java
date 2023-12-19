package com.team10.backoffice.domain.post.repository;

import com.querydsl.apt.jpa.JPAConfiguration;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles( "test" )
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
class PostQueryRepositoryTest {

	@Test
	void findOrderByContentLengthDesc() {
	}

	@Test
	void search() {
	}
}