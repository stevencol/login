package com.libros.libros.model.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "code")
    private String code;
    @Column(name = "status")
    private Boolean status;

    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "documento")
    private String documento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> roles;




}
