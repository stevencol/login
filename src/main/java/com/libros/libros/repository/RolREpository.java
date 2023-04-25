package com.libros.libros.repository;

import com.libros.libros.model.entitys.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

@Repository
public interface RolREpository extends JpaRepository<Rol, Long> {
    @Query("SELECT u FROM Rol u WHERE u.name = :name")
    Role findByName(@Param("name") String name);
}
