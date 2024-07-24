package com.est.cranejob.user.dto.request;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.util.Role;
import com.est.cranejob.user.util.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

	private String username;
	private String password;
	private String nickname;
	private UserStatus userStatus;
	private Role role;

	public User toEntity() {
		return User.builder()
			.username(username)
			.password(password)
			.nickname(nickname)
			.userStatus(UserStatus.ACTIVE)
			.role(Role.USER)
			.build();
	}
}
