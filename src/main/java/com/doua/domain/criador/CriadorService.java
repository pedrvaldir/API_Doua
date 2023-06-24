package com.doua.domain.criador;

import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CriadorService {

    @Autowired
    private CriadorRepository repositorio;

    public void setRepository(CriadorRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Criador> getClientes() {
        return repositorio.findAll();
    }

    public Optional<Criador> getDoadorPorId(Long id) {
        return repositorio.findById(id);
    }

    public List<Criador> getDoadorPorEmail(String email) {
        return repositorio.findByEmail(email);
    }


    public Criador save(Criador criador) {

        List<Criador> listaCriadores = getDoadorPorEmail(criador.getEmail());

        if (listaCriadores.isEmpty()) {
            return repositorio.save(criador);
        } else {
            return null;
        }

    }

    public Criador update(Criador novosDadosDoador, Long id) {

        Assert.notNull(id, Strings.ERRO_ATUALIZAR_REGISTRO);

        Optional<Criador> cliente = getDoadorPorId(id);

        if (cliente.isPresent()) {
            return atualizaDoador(novosDadosDoador, cliente);
        } else {
            throw new RuntimeException(Strings.ERRO_ATUALIZAR_REGISTRO);
        }
    }

    private Criador atualizaDoador(Criador doador, Optional<Criador> cliente) {
        cliente.get().setNome(doador.getNome());
        cliente.get().setEmail(doador.getEmail());

        return repositorio.save(cliente.get());
    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
