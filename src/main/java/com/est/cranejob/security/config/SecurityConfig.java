package com.est.cranejob.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/", "/user/**", "/user/login", "/user/signup", "/post/**", "/recruit/**")
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
				.requestMatchers("/", "/user/login", "/user/signup", "/post/list","/post/detail/**", "/recruit/**").permitAll()
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/user/login")
				.loginProcessingUrl("/user/login")  // 사용자 로그인 처리 URL
				.defaultSuccessUrl("/post/list", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/post/list")
				.permitAll()
			)
			.authenticationProvider(authenticationProvider);

		return http.build();
	}

	@Bean
	public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/","/admin/**", "/admin/login", "/admin/signup", "admin/users/**", "/post/**", "/announcements/**", "/recruit/**")
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*").permitAll()
				.requestMatchers("/", "/admin/login", "/admin/signup", "/recruit/**").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/admin/login")
				.loginProcessingUrl("/admin/login")  // 관리자 로그인 처리 URL
				.defaultSuccessUrl("/post/list", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/admin/logout")
				.logoutSuccessUrl("/post/list")
				.permitAll()
			)
			.authenticationProvider(authenticationProvider);

		return http.build();
	}
}
