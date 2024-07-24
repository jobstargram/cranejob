package com.est.cranejob.user.dto.response;

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
public class UserResponse {

	private Long userId;
	private String username;
	private String password;
	private String nickname;
	private UserStatus userStatus;
	private Role role;

	public static UserResponse toDto(User user) {
		return UserResponse.builder()
			.userId(user.getId())
			.username(user.getUsername())
			.password(user.getPassword())
			.nickname(user.getNickname())
			.userStatus(user.getUserStatus())
			.role(user.getRole())
			.build();
	}

}
