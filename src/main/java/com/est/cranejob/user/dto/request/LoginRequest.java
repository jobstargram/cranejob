package com.est.cranejob.user.dto.request;

import com.est.cranejob.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.est.cranejob.user.domain.User}
 */
@Data
@Builder
public class LoginRequest implements Serializable {

    // email 형식으로 작성(RFC 5322 정규식)
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
            , message = "이메일을 입력해 주세요.")
    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private String username;

    // 비밀번호: 영문, 숫자, 특수문자를 포함하여 8~15자 작성
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$"
            , message = "비밀번호를 입력해 주세요.")
    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    private String password;
}