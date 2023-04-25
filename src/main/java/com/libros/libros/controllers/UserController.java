package com.libros.libros.controllers;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libros.libros.mail.MailSender;
import com.libros.libros.model.entitys.User;
import com.libros.libros.model.services.ImplServices.ImpUserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	ImpUserService service;

	@Autowired
	MailSender maiLs;

	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute("user", new User());
		return "formUser.html";
	}

	@PostMapping("/save")
	public String save(User user, RedirectAttributes mesages) {
		try {
			user.setCode(maiLs.sendCodeVerification(user.getCorreo(), user.getNombre()));
			user.setStatus(false);

			service.save(user);
		} catch (DataException | MessagingException e) {

			System.out.println("Se genero error al guardar datos");
		}

		mesages.addFlashAttribute("registro", "Registro completo verifica  tu correo : " + user.getCorreo());

		return "redirect:/libro/lista";

	}

	@GetMapping("/activate")
	public String activate(@RequestParam String code, RedirectAttributes mesages) {

		User user = service.active(code);
		if (user != null) {
			user.setStatus(true);
			user.setCode("");
			service.save(user);
			mesages.addFlashAttribute("ok", "Usuario Activado");

		} else {
			mesages.addFlashAttribute("fail", "Link no valido");
		}

		return "redirect:/libro/lista";

	}

	@GetMapping("/formLogin")
	public String register() {

		return "login.html";
	}

	@GetMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes mesages) {

		String correo = request.getParameter("username");
		String contrasena = request.getParameter("pasword");

		User user = service.login(contrasena, correo);
		System.out.println("---user---");
		System.out.println(user);
		System.out.println("-------");

		if (user == null) {
			mesages.addFlashAttribute("fail", "Usuario o contraseñas no validos");
			return "redirect:/libro/lista";
		} else if (user.getStatus() == false) {
			mesages.addFlashAttribute("fail", "ACtiva el usuaio primero");
		} else if (user.getStatus() == true) {
			mesages.getFlashAttributes().remove("fail");
			mesages.addFlashAttribute("ok", user.getNombre() + ": ha iniciado correctamente");
		}

		return "redirect:/libro/lista";
	}

}
