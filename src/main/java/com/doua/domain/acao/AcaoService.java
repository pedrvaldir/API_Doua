package com.doua.domain.acao;

import com.doua.domain.tipoacao.TipoAcao;
import com.doua.domain.tipoacao.TipoAcaoRepository;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcaoService {

    @Autowired
    private AcaoRepository repositorio;

    public void setRepository(AcaoRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Acao> getAcoes() {
        return repositorio.findAll();
    }

    public Optional<Acao> getAcaoPorId(Long id) {
        return repositorio.findById(id);
    }

    public Acao save(Acao criador) {

        return repositorio.save(criador);

    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
