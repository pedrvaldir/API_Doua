package com.doua;

import com.doua.api.PedidoController;
import com.doua.domain.itemDoPedido.ItemDoPedido;
import com.doua.domain.pedido.Pedido;
import com.doua.domain.pedido.PedidoRepository;
import com.doua.domain.pedido.PedidoService;
import com.doua.domain.produto.Produto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class PedidoApplicationTests {

    @Autowired
    public PedidoController controller;

    @Autowired
    public PedidoService service;

    public PedidoRepository repository;

    @Before
    public void setup() {
        controller = new PedidoController();
        service = new PedidoService();
        repository = Mockito.mock(PedidoRepository.class);
        service.setRepository(repository);
    }

    @Test
    void deveBuscarPedidos() {

        ResponseEntity<HashMap<String, String>> retorno = controller.get();

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void deveInserirPedido() {
        Produto produto = new Produto();
        produto.setId(5l);
        produto.setDescricao("Apontador");

        ItemDoPedido itemDoPedido = new ItemDoPedido();
        itemDoPedido.setIdItemDoPedido(100l);
        itemDoPedido.setProduto(produto);
        itemDoPedido.setQuantidade(10);
        itemDoPedido.setIdCliente("1");

        List<ItemDoPedido> listPedido = new ArrayList<ItemDoPedido>();
        listPedido.add(itemDoPedido);

        Pedido pedido = new Pedido();
        pedido.setIdPedido(100l);
        pedido.setData("");
        pedido.setIdCliente("1");
        pedido.setItensDoPedido(listPedido);

        ResponseEntity<HashMap<String, String>> retorno = controller.post(pedido);

        Assert.assertEquals(HttpStatus.OK, retorno.getStatusCode());
        Assert.assertEquals("{Status=Pedido inclu??do com sucesso}", Objects.requireNonNull(retorno.getBody()).toString());
    }

    @Test
    void deveRetornarErroInserirPedido() {
        Pedido pedido = new Pedido();

        ResponseEntity<HashMap<String, String>> retorno = controller.post(pedido);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=N??o foi possivel incluir esse pedido}", Objects.requireNonNull(retorno.getBody()).toString());
    }

    @Test
    void deveRetornarErroBuscarPedidoId() {

        ResponseEntity<HashMap<String, String>> retorno = controller.get(0l);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
        Assert.assertEquals("{Erro=N??o foi possivel encontrar esse pedido}", Objects.requireNonNull(retorno.getBody()).toString());
    }
}
