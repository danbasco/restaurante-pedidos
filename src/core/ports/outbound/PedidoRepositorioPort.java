package core.ports.outbound;

import core.domain.Pedido;

// Interface para o Adapter de Persistência de Pedido
public interface PedidoRepositorioPort {

    public boolean salvar(Pedido pedido);
    public boolean atualizar(Pedido pedido);
    public Pedido buscarPorId(long pedidoId);
    public boolean cancelar(long pedidoId);
}
