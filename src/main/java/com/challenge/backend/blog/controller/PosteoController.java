package com.challenge.backend.blog.controller;

import com.challenge.backend.blog.exception.DatosErróneosException;
import com.challenge.backend.blog.models.Posteo;
import com.challenge.backend.blog.service.PosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PosteoController {

    @Autowired
    private PosteoService posteoService;

    @GetMapping
    public List<Posteo> listadoPosteos(HttpServletRequest request) {
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String titulo = request.getParameter("titulo");

        if(title != null){
            return this.posteoService.findByTitulo(title);
        } else if(category != null){
            return this.posteoService.findPosteosByCategoria(category);
        } else if (titulo != null && category != null){
            return this.posteoService.findPosteosByTituloAndCategoria(titulo, category);
        } else{
            return posteoService.listadoPosteosOrdenados();
        }
    }

    @GetMapping("/detalle")
    public List<Posteo> detallePosteos(){
        return posteoService.detallePosteos();
    }

    @PostMapping()
    public void guardarPosteo(@RequestBody Posteo posteo) {
        posteoService.save(posteo);
    }

    @GetMapping(path = "/{id}")
    public Posteo obtenerPosteoPorId(@PathVariable("id") Long id) {
        if(id == null){
            try {
                throw new DatosErróneosException("Error, el id ingresado no existe en la base de datos");
            } catch (DatosErróneosException e) {
                e.printStackTrace();
            }
        }
            return this.posteoService.obtenerPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.posteoService.delete(id);
        if (ok) {
            return "Se eliminó el posteo con id " + id;
        } else {
            return "No se pudo eliminar el posteo con id" + id;
        }

    }
}
