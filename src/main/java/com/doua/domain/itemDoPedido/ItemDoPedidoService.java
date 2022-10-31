package com.doua.domain.itemDoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemDoPedidoService {
	
	@Autowired
	private ItemDoPedidoRepository rep;
		
	public Iterable<ItemDoPedido> getItemDoPedido(){
		return rep.findAll();
	}
	
	public Optional<ItemDoPedido> getItemDoPedidoById(Long id){
		return rep.findById(id);
	}

	public ItemDoPedido save(ItemDoPedido ItemDoPedido) {
		return rep.save(ItemDoPedido);
	}

}
