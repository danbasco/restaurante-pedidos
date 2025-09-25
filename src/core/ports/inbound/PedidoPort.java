package core.ports.inbound;

import core.domain.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoPort {

    enum Status {
        AGUARDANDO, PREPARANDO, FINALIZADO,
        ENTREGANDO, ENTREGUE, CANCELADO
    }

    public Pedido criarPedido(Pedido pedido);
    public Pedido atualizarPedido(Pedido pedido);
    public Pedido consultarPedido(long id);
    public List<Pedido> listarPedidos();
    public boolean cancelarPedido(long id);

}
