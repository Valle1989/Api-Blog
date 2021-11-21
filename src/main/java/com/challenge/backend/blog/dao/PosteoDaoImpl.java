package com.challenge.backend.blog.dao;

import com.challenge.backend.blog.models.Categoria;
import com.challenge.backend.blog.models.Posteo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PosteoDaoImpl {

    @Autowired
    private PosteoDao posteoDao;


    public List<Posteo> ListPosteosOrdernadosDesc(){
        return posteoDao.ListPosteosOrdernadosDesc();
    }

    public Posteo save(Posteo posteo){
        return posteoDao.save(posteo);
    }

    public Posteo getPosteoByid(Long id){
        return posteoDao.findPosteoById(id);
    }

    public List<Posteo> getPosteoByTitulo(String titulo){
        return posteoDao.findByTitulo(titulo);
    }

    public List<Posteo> getPosteoByCategoria(String categoria){
        return (List<Posteo>) posteoDao.findByCategoria(categoria);
    }

    public List<Posteo> getPosteoByTituloAndCategoria(String titulo, String categoria){
        return posteoDao.findByTituloAndCategoria(titulo, categoria);
    }

}
