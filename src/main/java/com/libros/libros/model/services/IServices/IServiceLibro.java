package com.libros.libros.model.services.IServices;

import java.util.List;

import com.libros.libros.model.entitys.Editorial;
import com.libros.libros.model.entitys.Libro;

public interface IServiceLibro {

    List<Libro> getLibrosConEditorial();

    List<Libro> geLibros();

    void save(Libro libro);


    Libro findById(Long id);

    void delete(Libro libro);

}
