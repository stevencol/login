package com.libros.libros.model.services.ImplServices;

import com.libros.libros.model.entitys.Rol;
import com.libros.libros.model.entitys.User;
import com.libros.libros.model.services.IServices.IUserRolService;
import com.libros.libros.repository.IUserReposotory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private IUserReposotory reposotory;
	@Autowired
	private IUserRolService iUserRolService;

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		User user = reposotory.findByEmail(correo);
		if (user == null) {
			throw new UsernameNotFoundException("User no existe");
		}
		List<Rol> roles = iUserRolService.getRoleByUserId(user.getId());
		List<GrantedAuthority> ga = roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getContrasena(),
				user.getStatus(), true, true, true, ga);
	}

}
