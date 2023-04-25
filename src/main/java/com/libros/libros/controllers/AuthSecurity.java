package com.libros.libros.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope
public class AuthSecurity {

     @GetMapping("/auth/login")
    public String login(){

        return "login";
    }

    @GetMapping("/")
    public String  idexAuth(){
         return "redirect:libro/lista";
    }




}
