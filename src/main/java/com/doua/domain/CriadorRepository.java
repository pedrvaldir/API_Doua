package com.doua.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CriadorRepository extends CrudRepository<Criador, Long> {
	List<Criador> findByEmail(String email);
}


