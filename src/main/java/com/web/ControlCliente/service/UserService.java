package com.web.ControlCliente.service;

import com.web.ControlCliente.dao.IUserDao;
import com.web.ControlCliente.domain.Rol;
import com.web.ControlCliente.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")//Servicio para recuperar usuarios
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        //tipo para manejar roles GrantedAuthority
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : user.getRoles()) {
            //agrega el rol a la lista con JPA Spring security
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        }
        roles.forEach(System.out::println);
        //Creamos obj user pero asociado a spring security
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
