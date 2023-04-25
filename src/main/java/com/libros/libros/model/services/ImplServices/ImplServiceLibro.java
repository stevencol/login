package com.libros.libros.model.services.ImplServices;

import java.util.List;

import com.libros.libros.model.entitys.Editorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libros.libros.model.entitys.Libro;
import com.libros.libros.model.services.IServices.IServiceLibro;
import com.libros.libros.repository.IlibroRepository;

@Service
public class ImplServiceLibro implements IServiceLibro {

    @Autowired
    private IlibroRepository libroRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Libro> getLibrosConEditorial() {

        return libroRepository.findLibrodConeditorial();

    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> geLibros() {
        return libroRepository.findAll();
    }

    @Override
    public void save(Libro libro) {
        libroRepository.save(libro);

    }


    @Override
    @Transactional(readOnly = true)
    public Libro findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Libro libro) {
        libroRepository.deleteById(libro.getId());
    }


}
