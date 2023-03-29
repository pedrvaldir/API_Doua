package com.doua.domain.comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public List<Comentario> getComentariosPorAcao(Long idAcao) {
       // return repositorio.findByAcaoId(idAcao);
        return null;
    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
