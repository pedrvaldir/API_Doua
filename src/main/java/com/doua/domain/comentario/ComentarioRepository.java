package com.doua.domain.comentario;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends CrudRepository<Comentario, Long> {
    List<Comentario> findByAcaoId( Long idAcao);
}
