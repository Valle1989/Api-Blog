package com.challenge.backend.blog.dao;

import com.challenge.backend.blog.models.Posteo;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PosteoDaoImpl {

    @Autowired
    private PosteoDao posteoDao;

    @PersistenceContext
    private EntityManager entityManager;


    public List<Posteo> ListPosteosOrdernadosDesc(){
        return posteoDao.ListPosteosOrdernadosDesc();
    }

    public Posteo save(Posteo posteo){
        return posteoDao.save(posteo);
    }

    public Posteo update(Long id,Posteo posteo){
        Posteo post = posteoDao.findById(id).get();
        post.setTitulo(posteo.getTitulo());
        post.setContenido(posteo.getContenido());
        post.setImagen(posteo.getImagen());
        post.setCategoria(posteo.getCategoria());
        post.setFecha_creacion(posteo.getFecha_creacion());
        return posteoDao.save(post);
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

    public List<Posteo> findAllFilter(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedPosteoFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Posteo> posteos = posteoDao.findAll();
        session.disableFilter("deletedPosteoFilter");
        return posteos;
    }

}
