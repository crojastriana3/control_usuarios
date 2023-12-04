package com.web.ControlCliente.dao;

import com.web.ControlCliente.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserDao extends JpaRepository<User, Long> {
    //Método que usara spring para recuperar el usaurio por username
    User findByUsername(String username);
}
