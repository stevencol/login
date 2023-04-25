package com.libros.libros.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope
public class AuthSecurity {

	@GetMapping("/auth/login")
	public String login() {
		return "login";
	}

	@GetMapping("/errorhandler/{error}")
	public String errorlogin(@PathVariable String error, Model model) {
		model.addAttribute("message", error);
		return "login";
	}

}
