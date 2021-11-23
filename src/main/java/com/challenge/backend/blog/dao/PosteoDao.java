package com.challenge.backend.blog.dao;

import com.challenge.backend.blog.models.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteoDao extends JpaRepository<Posteo, Long> {

    @Query("select new Posteo(p.id, p.titulo, p.imagen, p.categoria, p.fecha_creacion) " +
            "FROM Posteo p order by p.fecha_creacion desc")
    public List<Posteo> ListPosteosOrdernadosDesc();

    @Query("from Posteo p where p.titulo =:titulo")
    public List<Posteo> findByTitulo(@Param("titulo") String titulo);

    @Query("from Posteo p where p.categoria.nombre =:categoria")
    public List<Posteo> findByCategoria(@Param("categoria") String categoria);

    @Query("select p.titulo, p.categoria from Posteo p where p.titulo =:titulo and p.categoria =:categoria")
    public List<Posteo> findByTituloAndCategoria(@Param("titulo") String titulo, @Param("categoria") String categoria);

    @Query("from Posteo p where p.id =:id")
    public Posteo findPosteoById(@Param("id") Long id);


}
