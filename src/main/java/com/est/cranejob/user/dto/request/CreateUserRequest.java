package com.est.cranejob.user.dto.request;

import com.est.cranejob.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.est.cranejob.user.domain.User}
 */
@Data
@Builder
public class CreateUserRequest implements Serializable {

    // RFC 5322 이메일 정규식
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
            ,message = "이메일을 입력해 주세요.")
    @NotBlank(message = "email은 빈칸을 입력할 수 없습니다.")
    private String username;

    @NotBlank(message = "이름은 빈칸을 입력할 수 없습니다.")
    private String name;

    // 비밀번호: 영문, 숫자, 특수문자를 포함하여 8~15자 작성
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$"
            ,message = "비밀번호는 10글자 ~ 15글자 사이여야 합니다")
    @NotBlank(message = "비밀번호는 빈칸을 입력할 수 없습니다.")
    private String password;

    public User toEntity() {
        return User.builder()
                .email(username)
                .password(password)
                .userName(name)
                .build();
    }
}