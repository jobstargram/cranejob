package com.est.cranejob.announcement.dto;

import com.est.cranejob.user.dto.request.UpdateUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.util.Role;
import com.est.cranejob.user.util.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminUpdateUserRequest {

	private String username;
	private String password;
	private String nickname;
	private Role role;
	private UserStatus userStatus;

	public static AdminUpdateUserRequest toResponseDto(UserResponse userResponse) {
		return AdminUpdateUserRequest.builder()
			.username(userResponse.getUsername())
			.password(userResponse.getPassword())
			.nickname(userResponse.getNickname())
			.role(userResponse.getRole())
			.userStatus(userResponse.getUserStatus())
			.build();
	}

}
