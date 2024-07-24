package com.est.cranejob.security.service;

import com.est.cranejob.user.domain.User;
import com.est.cranejob.user.dto.UserContext;
import com.est.cranejob.user.dto.response.UserResponse;
import com.est.cranejob.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));

		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().getValue()));

		return new UserContext(UserResponse.toDto(user), authorities);
	}
}
