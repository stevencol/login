package com.libros.libros.model.services.ImplServices;

import com.libros.libros.model.entitys.Rol;
import com.libros.libros.model.entitys.User;
import com.libros.libros.model.services.IServices.IUserService;
import com.libros.libros.repository.IUserReposotory;
import com.libros.libros.repository.RolREpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ImpUserService implements IUserService {

  @Autowired
  IUserReposotory reposotory;
  @Autowired
  private RolREpository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
    public void save(User user) {
       // user.setRoles(Arrays.asList(new Rol("user")));
      reposotory.save(user);
    }

  @Override
  public User active(String code) {
    return  reposotory.findByStatus(code);
  }

  @Override
  public User login(String contrasena, String correo) {
    System.out.println(reposotory.findLoggin(correo,contrasena));
    return reposotory.findLoggin(correo,contrasena);
  }

  @Override
  public User findUserByCorreo(String email) {
    return null;
  }


}
