package com.doua.api;

import java.util.HashMap;
import java.util.List;

import com.doua.domain.Doador;
import com.doua.domain.DoadorService;
import com.doua.domain.pedido.PedidoDTO;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doua.domain.itemDoPedido.ItemDoPedidoService;
import com.doua.domain.pedido.Pedido;
import com.doua.domain.pedido.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private DoadorService serviceCli;

    @Autowired
    private DoadorService doadorService;
    private ItemDoPedidoService servicePedido;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<HashMap<String, String>> get() {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;
        
        Iterable<PedidoDTO> pedidos = service.getPedido();
        if (pedidos != null) {
        	statusResponse =  new ResponseEntity(pedidos, HttpStatus.OK);
        } else {
            map.put(Strings.ERRO, Strings.ERRO_BUSCAR_PEDIDOS);
            statusResponse =  new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return statusResponse;
    }
    
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> get(@PathVariable("id") Long id) {
    	ResponseEntity<HashMap<String, String>> statusResponse;
    	
        try{
            PedidoDTO pedido = service.getPedidoPorId(id);
            if(pedido.getIdPedido()!=null)
            	statusResponse =  new ResponseEntity(pedido, HttpStatus.OK);
            else{
            	statusResponse =  disparaPedidoNaoEncontrado();
            }

        }catch (Exception ex){
        	statusResponse =  disparaPedidoNaoEncontrado();
        }
        return statusResponse;
    }
    
    private ResponseEntity<HashMap<String, String>> disparaPedidoNaoEncontrado() {
        HashMap<String, String> map = new HashMap<>();

        map.put(Strings.ERRO, Strings.ERRO_BUSCAR_PEDIDO_ID);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    
    @CrossOrigin
    @GetMapping("/ClienteCpf/{cpf}")
    public ResponseEntity<HashMap<String, String>> get(@PathVariable("cpf") String cpf) {
    	
    	HashMap<String, String> map = new HashMap<>();
    	ResponseEntity<HashMap<String, String>> statusResponse;

    	List<Doador> doadors = serviceCli.getDoadorPorCpf(cpf);
    	
    	if (doadors.isEmpty())
    	{
    		map.put(Strings.ERRO, Strings.ERRO_CLIENTE_NAO_ENCONTRADO);
    		statusResponse =  new ResponseEntity(map, HttpStatus.OK);
    	}else {
            statusResponse = buscarClienteCPF(map, doadors);
        }
    	return statusResponse;
    }

    private ResponseEntity<HashMap<String, String>> buscarClienteCPF(HashMap<String, String> map, List<Doador> doadors) {
        ResponseEntity<HashMap<String, String>> statusResponse;
        Doador doador;
        String idCliente = "";

        for (int i = 0; i < doadors.size(); i++) {
            doador = doadors.get(i);
            idCliente = doador.getId().toString();
        }

        List<PedidoDTO> pedidos = service.getPedidoPorIdCliente(idCliente);

        if(pedidos.isEmpty())
        {
            map.put(Strings.STATUS, Strings.SUCESSO_LISTA_PEDIDO_VAZIA);
            statusResponse =  new ResponseEntity(map, HttpStatus.OK);
        }else {
            statusResponse =  new ResponseEntity(pedidos, HttpStatus.OK);
        }
        return statusResponse;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Pedido itemPedido) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;
        try {
            service.save(itemPedido);
            map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_PEDIDO);
            statusResponse = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_PEDIDO);
            statusResponse = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return statusResponse;
    }
}
