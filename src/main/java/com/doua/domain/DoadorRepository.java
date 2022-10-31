package com.doua.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DoadorRepository extends CrudRepository<Doador, Long> {
	List<Doador> findByCpf(String cpf);
}


