package com.est.cranejob.user.dto.request;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.response.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAdminUserRequest {

	@NotBlank(message = "비밀번호는 빈칸을 입력할 수 없습니다.")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "현재 비밀번호를 입력해 주세요.")
	private String password;

	@NotBlank(message = "이름은 빈칸을 입력할 수 없습니다.")
	private String nickname;

	public static UpdateAdminUserRequest toResponseDto(UserResponse userResponse) {
		return UpdateAdminUserRequest.builder()
			.password(userResponse.getPassword())
			.nickname(userResponse.getNickname())
			.build();
	}

}
