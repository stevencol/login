package com.libros.libros.model.entitys;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@ToString
@Table(name = "libros")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Long id;

    @NotEmpty(message = "Ingresa un nombre")
    @Column(name = "nombre")
    @Size(max = 50)
    private String nombre;

    @NotEmpty(message = "Ingresa un Autor")
    @Column(name = "nombre_autor")
    @Size(max = 50)
    private String autor;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "editorial_id")
    // @ToString.Exclude
    //@JsonIgnore
    private Editorial editorial;


}
