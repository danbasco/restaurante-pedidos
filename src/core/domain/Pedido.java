package core.domain;

import core.ports.inbound.PedidoPort;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private long id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private PedidoPort.Status status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Pedido(long id, Cliente cliente, List<ItemPedido> itens, PedidoPort.Status status, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public PedidoPort.Status getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void setStatus(PedidoPort.Status status) {
        this.status = status;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public float getTotal() {
        float total = 0.0f;
        for (ItemPedido item : itens) {
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        return total;
    }
}