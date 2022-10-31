package com.doua.domain.usuario;

import com.doua.domain.usuario.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	List<Usuario>findByEmail(String email);
}


