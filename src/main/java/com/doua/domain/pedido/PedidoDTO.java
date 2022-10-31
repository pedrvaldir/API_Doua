package com.doua.domain.pedido;

import com.doua.domain.Doador;
import com.doua.domain.DoadorService;
import com.doua.domain.itemDoPedido.ItemDoPedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    private DoadorService serviceCliente;

    private Long idPedido;

    private String data;

    private Doador doador;

    private List<ItemDoPedido> itensDoPedido = new ArrayList<>();

    public PedidoDTO(Pedido p) {
        this.idPedido = p.getIdPedido();
        this.data = p.getData();
        this.itensDoPedido = p.getItensDoPedido();
    }

    public PedidoDTO() {
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data =  data;
    }

    public Doador getCliente() {
        return doador;
    }

    public void setCliente(Doador doador) {
        this.doador = doador;
    }

    public List<ItemDoPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItemDoPedido> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }

}