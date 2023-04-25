package com.libros.libros.controllers;

import com.libros.libros.model.entitys.Editorial;
import com.libros.libros.model.services.IServices.IEditorialService;
import jakarta.validation.Valid;
import com.libros.libros.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;

@Controller
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private IEditorialService editorialService;

    @Autowired
   private MailSender mailsneder;


    @GetMapping("/lista")
    public String getEditorial(Model model) {

        model.addAttribute("editoriales", editorialService.getEditoriales().stream().sorted(Comparator.comparing(Editorial::getId)));
        model.addAttribute("tituloPagina", "Lista Editoriales");
        return "editoriales";

    }


    @GetMapping("/form")
    public String getForm(Model model) {

        model.addAttribute("editorial", new Editorial());
        model.addAttribute("tituloPagina", "Agregar nuevo Editorial");
        System.out.println("xf");
        return "formEditorial.html";
    }

    @PostMapping("/save")
    public String saveEditorial(@Valid Editorial editorial, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "formEditorial.html";
        }

        editorialService.saveEditorial(editorial);

        return "redirect:/editorial/lista";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes messages) {
        System.out.println(id);
        if (editorialService.findById(id) != null) {

            editorialService.deleteEditorial(editorialService.findById(id));
        } else {
            messages.addAttribute("error", "Error al eliminar: id No es valido");
            return "redirect:/editorial/lista";
        }
        return "redirect:/editorial/lista";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, RedirectAttributes messages, Model model) {
        if (editorialService.findById(id) != null) {
            model.addAttribute("editorial", editorialService.findById(id));
            System.out.println(editorialService.findById(id).getId());
            return "formEditorial.html";
        } else {
            messages.addAttribute("error", "Error : id No es valido");
            return "redirect:/editorial/lista";
        }

    }

}
