package com.web.ControlCliente.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
    public static void main(String[] args) {

        var password = "123";
        System.out.println("Password: " + password);
        System.out.println("Passwoed encoder: " + encoderPassword(password));


    }

    public static String encoderPassword(String password) {
        //Clase spring para encriptar
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
