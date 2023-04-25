package com.libros.libros.model.services.IServices;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.libros.libros.model.entitys.Rol;
import com.libros.libros.model.entitys.UserRole;

public interface IUserRolService extends CrudRepository<UserRole, Long>{

	@Query("SELECT u.rol FROM UserRole u WHERE u.user.id = :userId")
	List<Rol> getRoleByUserId(@Param("userId") Long userId);
	
}
