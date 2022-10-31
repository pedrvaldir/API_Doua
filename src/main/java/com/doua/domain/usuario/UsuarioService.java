package com.doua.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    public void setRepository(UsuarioRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Usuario> getUsuarios() {
        return repositorio.findAll();
    }

    public Optional<Usuario> getUsuarioPorId(Long id) {
        return repositorio.findById(id);
    }

    public List<Usuario> getUsuarioPorEmail(String email) {
        return repositorio.findByEmail(email);
    }

    public Usuario save(Usuario novoUsuario) {

        List<Usuario> usuario = getUsuarioPorEmail(novoUsuario.getEmail());

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
