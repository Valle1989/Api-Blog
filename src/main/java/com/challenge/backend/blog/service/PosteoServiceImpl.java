package com.challenge.backend.blog.service;

import com.challenge.backend.blog.dao.PosteoDao;
import com.challenge.backend.blog.dao.PosteoDaoImpl;
import com.challenge.backend.blog.models.Posteo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PosteoServiceImpl implements PosteoService{

    @Autowired
    private PosteoDao posteoDao;

    @Autowired
    private PosteoDaoImpl posteoDaoImpl;

    @Override
    @Transactional(readOnly = true)
    public List<Posteo> listadoPosteosOrdenados() {
        return posteoDaoImpl.ListPosteosOrdernadosDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Posteo> detallePosteos() {
        return (List<Posteo>) posteoDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Posteo save(Posteo posteo) {
        return posteoDaoImpl.save(posteo);
    }

    @Override
    @Transactional(readOnly = true)
    public Posteo obtenerPorId(Long id) {
        return posteoDaoImpl.getPosteoByid(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Posteo> findByTitulo(String titulo) {
        return posteoDaoImpl.getPosteoByTitulo(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Posteo> findPosteosByCategoria(String categoria) {
        return posteoDaoImpl.getPosteoByCategoria(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Posteo> findPosteosByTituloAndCategoria(String titulo, String categoria) {
        return posteoDaoImpl.getPosteoByTituloAndCategoria(titulo, categoria);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        try {
            posteoDao.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
