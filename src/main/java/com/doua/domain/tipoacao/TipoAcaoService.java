package com.doua.domain.tipoacao;

import com.doua.domain.acao.Acao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<TipoAcao> getTipoAcaoPorId(Long id) {
        return repositorio.findById(id);
    }

    public TipoAcao save(TipoAcao localizacao) {

      return repositorio.save(localizacao);

    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
