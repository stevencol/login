package com.libros.libros.controllers;

import com.libros.libros.model.entitys.Editorial;
import com.libros.libros.model.entitys.Libro;
import com.libros.libros.model.services.IServices.IEditorialService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.libros.libros.model.services.IServices.IServiceLibro;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/libro")
//@SessionAttributes("editoriales")
public class LibroController {


    @ModelAttribute("editoriales")
    public List<Editorial> getEditoriales() {
        return editorialService.getEditoriales();
    }



    @Autowired
    private IServiceLibro libroService;
    @Autowired
    private IEditorialService editorialService;

    @ModelAttribute("libro")
    public Libro clearLibroModelAttribute() {
        return new Libro();
    }


    @GetMapping("/lista")
    public String listaLibros(Model model) {
        model.addAttribute("libros", libroService.getLibrosConEditorial().stream().sorted(Comparator.comparing(Libro::getId)));
        model.addAttribute("tituloPagina", "Lista Libros");


        return "libros";
    }


    @GetMapping("/form")
    public String getFormRegisterLibros(Model model, HttpServletResponse response) {

        model.addAttribute("libro", new Libro());
        model.addAttribute("tituloPagina", "Agregar nuevo Libro");
        model.addAttribute("editoriales", editorialService.getEditoriales()) ;


        return "formlibro.html";

    }


    @PostMapping("/save")
    public String saveLibros(@RequestParam("editorial_id") Long editorial_id, @Valid Libro libro, BindingResult result, Model model) {

        if (result.hasErrors() || editorialService.findById(editorial_id) == null) {
            result.addError(editorialService.findById(editorial_id) == null ? new FieldError("editorial", "editorial", "Editorial no encontrada") : null);
            model.addAttribute("libros", libroService.getLibrosConEditorial());
            return "formlibro.html";
        }
        libro.setEditorial(editorialService.findById(editorial_id));
        libroService.save(libro);
        return "redirect:/libro/lista";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes messages) {
        System.out.println(id);
        if (libroService.findById(id) != null) {
            System.out.println(libroService.findById(id));
            libroService.delete(libroService.findById(id));
        } else {
            messages.addAttribute("error", "Error al eliminar: id No es valido");
            return "redirect:/libro/lista";
        }
        return "redirect:/libro/lista";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, RedirectAttributes messages, Model model) {
        if (libroService.findById(id) != null) {
            model.addAttribute("libro", libroService.findById(id));
            model.addAttribute("editoriales", editorialService.getEditoriales());
            return "formlibro.html";
        } else {
            messages.addAttribute("error", "Error : id No es valido");
            return "redirect:/libro/lista";
        }

    }

}
