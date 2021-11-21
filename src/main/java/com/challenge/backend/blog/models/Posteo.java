package com.challenge.backend.blog.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "posteo")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Posteo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String contenido;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    private Date fecha_creacion;

    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Posteo() {
    }

    public Posteo(Long id, String titulo, String imagen, Date fecha_creacion) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.fecha_creacion = fecha_creacion;
    }

    public Posteo(Long id, String titulo, String imagen, Categoria categoria, Date fecha_creacion) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.categoria = categoria;
        this.fecha_creacion = fecha_creacion;
    }

}
