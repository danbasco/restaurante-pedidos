package core.ports.in;

import core.domain.Pedido;

public interface PedidoPort {

    enum Status {
        AGUARDANDO, PREPARANDO, FINALIZADO,
        ENTREGANDO, ENTREGUE, CANCELADO
    }

    public boolean criarPedido(Pedido pedido);
    public boolean atualizarPedido(Pedido pedido);
    public Status consultarStatusPedido(Pedido pedido);
    public boolean cancelarPedido(Pedido pedido);

}
