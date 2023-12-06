package com.team10.backoffice.domain.users.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRequestDto {
	private String username;
	private String password;
	private String email;
	private String introduce;
	private String role;
}
