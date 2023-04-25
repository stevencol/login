package com.libros.libros.model.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    List<UserRole> roles;


    public Rol(String name) {
        this.name = name;
    }


}
