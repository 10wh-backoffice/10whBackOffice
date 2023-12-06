package com.team10.backoffice.domain.users.dto;

import com.team10.backoffice.domain.users.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
	private String username;
	private String email;
	private String introduce;

	@Builder
	public UserResponseDto( User user ) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.introduce = user.getIntroduce();
	}
}