package com.libros.libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libros.libros.model.entitys.Libro;

import java.util.List;

@Repository
public interface IlibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT libro FROM Libro libro JOIN FETCH libro.editorial edit")
    List<Libro> findLibrodConeditorial();

}
