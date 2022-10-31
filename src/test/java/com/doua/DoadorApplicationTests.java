package com.doua;

import java.util.HashMap;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.doua.api.DoadorController;
import com.doua.domain.Doador;
import com.doua.domain.DoadorRepository;
import com.doua.domain.DoadorService;


@SpringBootTest
class DoadorApplicationTests {

	@Autowired
	private DoadorService doadorService;
	
    @Autowired
    public DoadorController controller;

    public DoadorRepository repository;

    public ExpectedException exception = ExpectedException.none();
	
    @Before
    public void setup() {
        controller = new DoadorController();
        doadorService = new DoadorService();
        repository = Mockito.mock(DoadorRepository.class);
        doadorService.setRepository(repository);
    }
    
 
	@Test
	void inserindoCliente() {
		Doador doador = new Doador();
		doador.setCpf("071.760.649-03");
		doador.setNome("Testes");
		doador.setSobrenome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Cliente incluido com sucesso, idCliente=" + doador.getId().toString() + "}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}

	@Test
	void IncluirClienteCpfExistente() {
		Doador doador = new Doador();
		doador.setCpf("071.760.649-03");
		doador.setNome("Testes");
		doador.setSobrenome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=CPF já existente}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteSemCPF() {
		Doador doador = new Doador();
		doador.setCpf("");
		doador.setNome("Testes");
		doador.setSobrenome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}

	@Test
	void IncluirClienteSemNome() {
		Doador doador = new Doador();
		doador.setCpf("071.218.839-88");
		doador.setNome("");
		doador.setSobrenome("Unitarios");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteSemSobrenome() {
		Doador doador = new Doador();
		doador.setCpf("071.218.839-88");
		doador.setNome("Testes");
		doador.setSobrenome("");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteEmBranco() {
		Doador doador = new Doador();
		doador.setCpf("");
		doador.setNome("");
		doador.setSobrenome("");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteVazio() {
		Doador doador = new Doador();
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Campos obrigatórios não preenchidos}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}
	
	@Test
	void IncluirClienteCPFIncorreto() {
		Doador doador = new Doador();
		doador.setCpf("071.133.332.88");
		doador.setNome("Testes");
		doador.setSobrenome("teste");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.post(doador);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=CPF inválido}", Objects.requireNonNull(retorno.getBody()).toString());
    
	}

	@Test
	void AtualizandoCliente() {
		Doador doador = new Doador();
		doador.setCpf("071.760.649-03");
		doador.setNome("Testes");
		doador.setSobrenome("Unitarios - Update");
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.put(15L, doador);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Cliente atualizado com sucesso, idCliente=" + doador.getId().toString() + "}", Objects.requireNonNull(retorno.getBody()).toString());
		
	}

	@Test
	void ExcluindoCliente() {
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.delete(16L);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Cliente excluido com sucesso}", Objects.requireNonNull(retorno.getBody()).toString());
	}
	
	@Test
	void ExcluindoClienteTemProduto() {
		
	    ResponseEntity<HashMap<String, String>> retorno = controller.delete(1L);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=Esse cliente está associado a Pedidos}", Objects.requireNonNull(retorno.getBody()).toString());

	}

	
	
}
