package com.libros.libros.model.services.ImplServices;

import com.libros.libros.model.entitys.User;
import com.libros.libros.repository.IUserReposotory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private IUserReposotory reposotory;
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        User user =  reposotory.findByEmail(correo);
        if(user!=null){
            return new UserDetail(user);
        }
        throw new UsernameNotFoundException("User no existe");
    }
}
