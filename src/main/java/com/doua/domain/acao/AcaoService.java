package com.doua.domain.acao;

import com.doua.domain.localizacao.Localizacao;
import com.doua.domain.localizacao.LocalizacaoRepository;
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

    @Autowired
    private LocalizacaoRepository repoLocal;

    public void setRepository(AcaoRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Acao> getAcoes() {
        return repositorio.findAll();
    }

    public Optional<Acao> getAcaoPorId(Long id) {
        return repositorio.findById(id);
    }

    public Acao save(Acao acao) {
        Localizacao  result = repoLocal.save(acao.getLocalizacao());

        if(result!=null){
            acao.setLocalizacao(result);
            return repositorio.save(acao);
        }
            return null;
    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
