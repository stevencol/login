package com.libros.libros.model.services.ImplServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.libros.libros.model.entitys.User;
import com.libros.libros.model.entitys.UserRole;
import com.libros.libros.model.services.IServices.IUserRolService;
import com.libros.libros.model.services.IServices.IUserService;
import com.libros.libros.repository.IUserReposotory;
import com.libros.libros.repository.RolREpository;

@Service
public class ImpUserService implements IUserService {

	@Autowired
	IUserReposotory reposotory;
	@Autowired
	private RolREpository roleRepository;

	@Autowired
	private IUserRolService iUserRolService;

	@Override
	public void save(User user) {
		// user.setRoles(Arrays.asList(new Rol("user")));
		user.setContrasena(new BCryptPasswordEncoder().encode(user.getContrasena()));
		UserRole rolEntity = UserRole.builder().user(user).rol(roleRepository.findById(1L).orElse(null)).build();
		iUserRolService.save(rolEntity);
	}

	@Override
	public User active(String code) {
		return reposotory.findByStatus(code);
	}

	@Override
	public User login(String contrasena, String correo) {
		System.out.println(reposotory.findLoggin(correo, contrasena));
		return reposotory.findLoggin(correo, contrasena);
	}

	@Override
	public User findUserByCorreo(String email) {
		return null;
	}

}
