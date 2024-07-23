package com.est.cranejob.user.dto;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.domain.UserStatus;
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
public class CreateUser {

	private String username;
	private String password;
	private String name;

	public User toEntity() {
		return User.builder()
			.email(username)
			.password(password)
			.userName(name)
			.userStatus(UserStatus.ACTIVE)
			.build();
	}

}
