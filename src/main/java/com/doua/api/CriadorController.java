package com.doua.api;

import com.doua.domain.Criador;
import com.doua.domain.CriadorService;
import com.doua.domain.pedido.PedidoDTO;
import com.doua.domain.pedido.PedidoService;
import com.doua.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/criadores")
public class CriadorController {
	
	@Autowired
	private CriadorService service;
	
	@Autowired
	private PedidoService pedidoservice;
	
	 @CrossOrigin
	@GetMapping()
	public ResponseEntity<Iterable<Criador>> get() {
		return new ResponseEntity<>(service.getClientes(),HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Criador> get(@PathVariable("id") Long id) {
		Optional<Criador> doador = service.getDoadorPorId(id);
		ResponseEntity statusResponse;
		
		if(doador.isPresent())
		{
			statusResponse =  ResponseEntity.ok(doador.get());
		}else {
			statusResponse =  ResponseEntity.notFound().build();
		}	
		
		return statusResponse;
	}
	
	@CrossOrigin
	@GetMapping("/cpf/{cpf}")
	public  ResponseEntity<List<Criador>> get(@PathVariable("cpf") String cpf) {
		List<Criador> doador = service.getDoadorPorCpf(cpf);
		ResponseEntity statusResponse;
		
		if(doador.isEmpty())
		{
			statusResponse =  ResponseEntity.noContent().build();
		}else {
			statusResponse =  ResponseEntity.ok(doador);
		}
		return statusResponse;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<HashMap<String, String>> post(@RequestBody Criador doador) {
		HashMap<String, String> map = new HashMap<>();
		ResponseEntity<HashMap<String, String>> statusResponse;
		
		if(criadorInvalido(doador))
		{
			map.put(Strings.ERRO,Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
			statusResponse =  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);

		}else {
			Criador postCriador = service.save(doador);
			if(postCriador == null)
			{
				map.put(Strings.ERRO,Strings.ERRO_CPF_EXISTENTE);
				statusResponse =  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);		
			}	
			else {
				map.put("idCriador", postCriador.getIdCriador().toString());
				map.put(Strings.STATUS,Strings.SUCESSO_INCLUIR_CRIADOR);
				statusResponse =  new ResponseEntity<>(map,HttpStatus.OK);					
			}
		}
		return statusResponse;
	}

	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<HashMap<String, String>> put(@PathVariable("id") Long id, @RequestBody Criador doador) {
        HashMap<String, String> map = new HashMap<>();
        ResponseEntity<HashMap<String, String>> statusResponse;
        
        if (criadorInvalido(doador))
        {
			map.put(Strings.ERRO,Strings.ERRO_INCLUIR_CAMPOS_OBRIGATORIOS);
			statusResponse =  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }else {
			Criador cli = service.update(doador, id);
        	
			map.put("idCliente",cli.getIdCriador().toString());
			map.put(Strings.STATUS,Strings.SUCESSO_ATUALIZAR_CLIENTE);
			statusResponse =  new ResponseEntity<>(map,HttpStatus.OK);	
        }
        return statusResponse;
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<HashMap<String, String>> delete(@PathVariable("id") Long id) {
		HashMap<String, String> map = new HashMap<>();
		List<PedidoDTO> pedido = pedidoservice.getPedidoPorIdCliente(id.toString());
		HttpStatus statusResponse;
		
		if(pedido.isEmpty()) {
			service.delete(id);
			
			map.put(Strings.STATUS,Strings.SUCESSO_EXCLUSAO_CLIENTE);
			statusResponse = HttpStatus.OK;	
		}else {
			map.put(Strings.ERRO,Strings.ERRO_CLIENTE_TEM_PEDIDO);
			statusResponse =  HttpStatus.BAD_REQUEST;	
		}
		
		return new ResponseEntity<>(map,statusResponse);	
	}
	
    private boolean criadorInvalido(Criador criador) {
        return criador.getEmail().equals("") | criador.getToken().equals("") | criador.getNome().equals("")
        		| criador.getEmail() == null | criador.getToken() == null | criador.getNome() == null;
    }
}
