package com.doua.domain.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repositorio;

    public void setRepository(LocalizacaoRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Localizacao> getLocalizacoes() {
        return repositorio.findAll();
    }

    public Localizacao save(Localizacao localizacao) {

      return repositorio.save(localizacao);

    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
