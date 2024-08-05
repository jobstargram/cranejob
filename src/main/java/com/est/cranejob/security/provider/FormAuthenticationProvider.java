package com.est.cranejob.security.provider;

import com.est.cranejob.user.dto.UserContext;
import com.est.cranejob.user.util.UserStatus;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component("authenticationProvider")
@RequiredArgsConstructor
public class FormAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String loginId = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserContext userContext = (UserContext) userDetailsService.loadUserByUsername(loginId);

		if (userContext.getUserResponse().getUserStatus() == UserStatus.SUSPENDED) {
			throw new BadCredentialsException("User account is suspended");
		}

		if (!passwordEncoder.matches(password, userContext.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}

		// 관리자 로그인, 일반 사용자 로그인 구분 로직
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String requestURI = request.getRequestURI();

		// Check role and request URI to restrict access
		if (requestURI.contains("/admin/login") && userContext.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			throw new BadCredentialsException("User does not have admin privileges");
		} else if (requestURI.contains("/user/login") && userContext.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			throw new BadCredentialsException("Admin cannot login to user page");
		}

		return new UsernamePasswordAuthenticationToken(userContext.getUserResponse(), null, userContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}
}
