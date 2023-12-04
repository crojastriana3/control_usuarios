package com.web.ControlCliente.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name = "person")
public class IPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //validaciones a través de notaciones
    @NotEmpty(message = "Name is null")
    private String name;
    @NotEmpty(message = "Last Name is null")
    private String lastName;
    @NotEmpty(message = "Email is null")
    @Email(message = "Email not is valid")
    private String email;

    private String phone;
}
