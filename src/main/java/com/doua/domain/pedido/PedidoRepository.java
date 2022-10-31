package com.doua.domain.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByidCliente(String idCliente);
		
}

