package com.team10.backoffice.domain.users.service;

import com.team10.backoffice.domain.users.dto.UserPasswordDto;
import com.team10.backoffice.domain.users.dto.UserRequestDto;
import com.team10.backoffice.domain.users.dto.UserResponseDto;
import com.team10.backoffice.domain.users.entity.User;
import com.team10.backoffice.domain.users.entity.UserRoleEnum;
import com.team10.backoffice.domain.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void signup( UserRequestDto userRequestDto ) {

		if (userRepository.existsByUsernameOrEmailOrNickname(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getNickname())) {
			throw new DuplicateKeyException("이미 회원가입된 사용자입니다");
		}

		String password = passwordEncoder.encode( userRequestDto.getPassword() );

		User user = new User();
		user.setUsername( userRequestDto.getUsername() );
		user.setPassword( password );
		user.setEmail( userRequestDto.getEmail() );
		user.setIntroduce( userRequestDto.getIntroduce() );
		user.setRole( UserRoleEnum.USER );

		this.userRepository.save( user );
	}

	public UserResponseDto getUser(long userId) {
		var user = this.userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("user id : " + userId + " not exist."));

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUsername(user.getUsername());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setIntroduce(user.getIntroduce());

		return userResponseDto;
	}

	@Transactional
	public void updateUser(long userId, UserRequestDto userRequestDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("user id : " + userId + " not exist."));

		user.setUsername(userRequestDto.getUsername());
		user.setIntroduce(userRequestDto.getIntroduce());
		user.setEmail(userRequestDto.getEmail());
	}

	@Transactional
	public void updatePassword(long userId, UserPasswordDto userPasswordDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("user id : " + userId + " not exist."));

		if (!passwordEncoder.matches(userPasswordDto.getCurrentPassword(), user.getPassword())) {
			throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다");
		}

		if (!userPasswordDto.getNewPassword().equals(userPasswordDto.getCheckPassword())) {
			throw new IllegalArgumentException("입력하신 비밀번호가 서로 다릅니다");
		}

		if (isPasswordInOldPasswords(user, userPasswordDto.getNewPassword())) {
			throw new IllegalArgumentException("최근에 교체한 비밀번호입니다 다른 비밀번호를 입력해주세요");
		}
		user.setPassword(passwordEncoder.encode(userPasswordDto.getNewPassword()));
		updateOldPasswords(user,userPasswordDto.getNewPassword());
	}

	private boolean isPasswordInOldPasswords(User user, String newPassword) {
		List<String> oldPasswords = user.getOldPasswords();
		return oldPasswords.stream().limit(3).anyMatch(oldPassword -> passwordEncoder.matches(newPassword,oldPassword));
	}

	private void updateOldPasswords(User user, String newPassword) {
		List<String> oldPasswords = user.getOldPasswords();
		oldPasswords.add(0, passwordEncoder.encode(newPassword));

		if (oldPasswords.size() > 3) {
			oldPasswords = oldPasswords.subList(0, 3);
		}

		user.setOldPasswords(oldPasswords);
	}
}