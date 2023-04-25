package com.libros.libros.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.libros.libros.AuthFaildHandler;
import com.libros.libros.model.services.ImplServices.UserDetailService;

@Configuration
@EnableWebSecurity
public class Sercurirty {
	@Autowired
	private UserDetailService userDetailService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.authorizeHttpRequests(
						(request) -> request
								.requestMatchers("/libro/lista", "/upload/**", "user/register", "user/save",
										"/user/activate/**", "/user/login", "/login")
								.permitAll().anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/auth/login").defaultSuccessUrl("/libro/lista", true).permitAll())
				.logout((logout) -> logout.permitAll());
		return httpSecurity.build();

	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new AuthFaildHandler();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;

	}

}
