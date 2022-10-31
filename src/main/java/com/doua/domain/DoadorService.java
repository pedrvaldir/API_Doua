package com.doua.domain;

import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository repositorio;

    public void setRepository(DoadorRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Doador> getClientes() {
        return repositorio.findAll();
    }

    public Optional<Doador> getDoadorPorId(Long id) {
        return repositorio.findById(id);
    }

    public List<Doador> getDoadorPorCpf(String cpf) {
        return repositorio.findByCpf(cpf);
    }

    public Doador save(Doador doador) {

        List<Doador> doadors = getDoadorPorCpf(doador.getCpf());

        if (doadors.isEmpty()) {
            return repositorio.save(doador);
        } else {
            return null;
        }

    }

    public Doador update(Doador novosDadosDoador, Long id) {

        Assert.notNull(id, Strings.ERRO_ATUALIZAR_REGISTRO);

        Optional<Doador> cliente = getDoadorPorId(id);

        if (cliente.isPresent()) {
            return atualizaDoador(novosDadosDoador, cliente);
        } else {
            throw new RuntimeException(Strings.ERRO_ATUALIZAR_REGISTRO);
        }
    }

    private Doador atualizaDoador(Doador doador, Optional<Doador> cliente) {
        cliente.get().setNome(doador.getNome());
        cliente.get().setSobrenome(doador.getSobrenome());

        return repositorio.save(cliente.get());
    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
