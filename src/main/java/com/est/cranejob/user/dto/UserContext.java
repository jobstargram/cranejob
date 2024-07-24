package com.est.cranejob.user.dto;

import com.est.cranejob.user.dto.request.CreateUserRequest;
import com.est.cranejob.user.dto.response.UserResponse;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserContext implements UserDetails {

	private final UserResponse userResponse;
	private final List<GrantedAuthority> authorities;

	public UserContext(UserResponse userResponse, List<GrantedAuthority> authorities) {
		this.userResponse = userResponse;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return userResponse.getPassword();
	}

	@Override
	public String getUsername() {
		return userResponse.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
