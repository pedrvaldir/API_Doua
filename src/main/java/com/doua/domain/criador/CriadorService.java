package com.doua.domain.criador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CriadorService {

    @Autowired
    private CriadorRepository repositorio;

    public void setRepository(CriadorRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Criador> getCriadores() {
        return repositorio.findAll();
    }

    public Optional<Criador> getCriadorPorId(Long id) {
        return repositorio.findById(id);
    }

    public List<Criador> getCriadorPorEmail(String email) {
        return repositorio.findByEmail(email);
    }

    public Criador save(Criador novoUsuario) {

        List<Criador> usuario = getCriadorPorEmail(novoUsuario.getEmail());

        if (usuario.isEmpty()) {
            return repositorio.save(novoUsuario);
        } else {
            return null;
        }

    }

//    public Usuario update(Usuario novoUsuario, String email) {
//
//        Assert.notNull(email, Strings.ERRO_ATUALIZAR_REGISTRO);
//
//        Optional<Usuario> usuario = getUsuarioPorId(email);
//
//        if (usuario.isPresent()) {
//            return atualizaDoador(novoUsuario, usuario);
//        } else {
//            throw new RuntimeException(Strings.ERRO_ATUALIZAR_REGISTRO);
//        }
//    }

//    private Usuario atualizaDoador(Usuario user, Optional<Usuario> usuario) {
//        usuario.get().setNome(user.getNome());
//        usuario.get().setNome(user.getNome());
//
//        return repositorio.save(usuario.get());
//    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
