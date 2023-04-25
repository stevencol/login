package com.libros.libros;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFaildHandler implements AuthenticationFailureHandler{

	private Logger log = LoggerFactory.getLogger(AuthFaildHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.error(exception.getMessage());
		response.sendRedirect("/errorhandler/" + exception.getMessage());
	}
	
}
