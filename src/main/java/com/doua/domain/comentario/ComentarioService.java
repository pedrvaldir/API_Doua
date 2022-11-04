package com.doua.domain.comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repositorio;

    public void setRepository(ComentarioRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Comentario> getComentarios() {
        return repositorio.findAll();
    }

    public Comentario save(Comentario comentario) {

      return repositorio.save(comentario);

    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
