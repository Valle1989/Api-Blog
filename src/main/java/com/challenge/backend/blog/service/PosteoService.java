package com.challenge.backend.blog.service;


import com.challenge.backend.blog.models.Posteo;

import java.util.List;

public interface PosteoService {

    public List<Posteo> listadoPosteosOrdenados();

    public List<Posteo> detallePosteos();

    public Posteo save(Posteo posteo);

    public Posteo update(Long id, Posteo posteo);

    public Posteo obtenerPorId(Long id);

    public List<Posteo> findByTitulo(String titulo);

    public List<Posteo> findPosteosByCategoria(String categoria);

    public List<Posteo> findPosteosByTituloAndCategoria(String titulo, String categoria);

    public boolean delete(Long id);

    public List<Posteo> findAllFilter(boolean isDeleted);

}
