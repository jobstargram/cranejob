package com.est.cranejob.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String errorMessage;

		if (exception.getMessage().equalsIgnoreCase("User account is suspended")) {
			errorMessage = "suspended";
		} else if (exception.getMessage().equalsIgnoreCase("Invalid password")) {
			errorMessage = "invalid_password";
		} else if (exception.getMessage().equalsIgnoreCase("User does not have admin privileges")) {
			errorMessage = "no_admin_privileges";
		} else if (exception.getMessage().equalsIgnoreCase("Admin cannot login to user page")) {
			errorMessage = "admin_login_not_allowed";
		} else {
			errorMessage = "unknown";
		}

		String requestURI = request.getRequestURI();

		if (requestURI.contains("/admin/login")) {
			getRedirectStrategy().sendRedirect(request, response, "/admin/login?error=" + errorMessage);
		} else if (requestURI.contains("/user/login")) {
			getRedirectStrategy().sendRedirect(request, response, "/user/login?error=" + errorMessage);
		}
	}
}