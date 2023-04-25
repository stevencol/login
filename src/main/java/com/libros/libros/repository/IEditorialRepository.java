package com.libros.libros.repository;

import com.libros.libros.model.entitys.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEditorialRepository extends JpaRepository<Editorial, Long> {
}
