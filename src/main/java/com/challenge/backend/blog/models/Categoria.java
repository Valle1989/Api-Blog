package com.challenge.backend.blog.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "categoria")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = { CascadeType.ALL})
    private List<Posteo> posteos;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(String nombre, List<Posteo> posteos) {
        this.nombre = nombre;
        this.posteos = posteos;
    }
}
