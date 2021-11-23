package com.challenge.backend.blog.controller;

import com.challenge.backend.blog.models.Posteo;
import com.challenge.backend.blog.service.PosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        if (title != null) {
            return this.posteoService.findByTitulo(title);
        } else if (category != null) {
            return this.posteoService.findPosteosByCategoria(category);
        } else if (titulo != null && category != null) {
            return this.posteoService.findPosteosByTituloAndCategoria(titulo, category);
        } else {
            return posteoService.listadoPosteosOrdenados();
        }
    }

    @GetMapping("/detalle")
    public List<Posteo> detallePosteos() {
        return posteoService.detallePosteos();
    }

    @PostMapping()
    public void guardarPosteo(@RequestBody Posteo posteo) {
        posteoService.save(posteo);
    }

    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long id, @RequestBody Posteo posteo) {
        try{
            posteoService.update(id, posteo);
            return new ResponseEntity<>("Posteo actualizado", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("No existe el id especificado " + e.toString(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerPosteoPorId(@PathVariable("id") Long id) {
        try{
            Posteo post = posteoService.obtenerPorId(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch(Exception e){
            e.getMessage();
            return new ResponseEntity<>("No existe el id que se quiere obtener " + e.toString(), HttpStatus.CONFLICT);
        }
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

    @GetMapping(value = "/borrados")
    public ResponseEntity<List<Posteo>> findAll(@RequestParam(value =
            "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        List<Posteo> posteos = posteoService.findAllFilter(isDeleted);
        return new ResponseEntity<>(posteos, HttpStatus.OK);
    }
}

