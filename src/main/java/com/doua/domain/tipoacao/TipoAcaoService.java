package com.doua.domain.tipoacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoAcaoService {

    @Autowired
    private TipoAcaoRepository repositorio;

    public void setRepository(TipoAcaoRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<TipoAcao> getTipoAcoes() {
        return repositorio.findAll();
    }

    public TipoAcao save(TipoAcao localizacao) {

      return repositorio.save(localizacao);

    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
