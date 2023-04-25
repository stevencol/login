package com.libros.libros.model.services.IServices;

import com.libros.libros.model.entitys.Libro;
import com.libros.libros.model.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IUserService  {


    void save(User user);

    User active(String code);

    User login(String contrasena ,String correo);
    User findUserByCorreo(String correo);


}
