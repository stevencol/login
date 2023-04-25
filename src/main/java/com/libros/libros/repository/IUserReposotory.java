package com.libros.libros.repository;

import com.libros.libros.model.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserReposotory extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.code = :code AND u.status=false")
   public  User findByStatus(@Param("code") String code);

    @Query("SELECT u FROM User u WHERE u.correo = :correo AND u.contrasena = :contrasena")
    public User findLoggin(@Param("correo") String correo, @Param("contrasena") String contrasena);

    @Query("SELECT u FROM User u WHERE u.correo = :correo")
    User findByEmail(@Param("correo") String correo);

}

