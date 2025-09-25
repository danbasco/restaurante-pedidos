package core.ports.inbound;

import core.domain.Pedido;

import java.util.UUID;

public interface PedidoPort {

    enum Status {
        AGUARDANDO, PREPARANDO, FINALIZADO,
        ENTREGANDO, ENTREGUE, CANCELADO
    }

    public Pedido criarPedido(Pedido pedido);
    public boolean atualizarPedido(UUID pedidoId, Pedido pedidoAtualizado);
    public Status consultarStatusPedido(UUID pedidoId);
    public boolean cancelarPedido(UUID pedidoId);

}
