package com.web.ControlCliente.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    //Relación rol y usuario
    @OneToMany //uno a muchos
    @JoinColumn(name = "id_user")
    private List<Rol> roles;

}
