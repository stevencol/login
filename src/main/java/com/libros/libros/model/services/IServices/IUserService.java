package com.libros.libros.model.services.IServices;

import com.libros.libros.model.entitys.User;

public interface IUserService {

	User active(String code);

	User login(String contrasena, String correo);

	User findUserByCorreo(String correo);

	void save(User user);

}
