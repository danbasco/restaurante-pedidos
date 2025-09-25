package adapters.outbound;

import core.domain.Pedido;
import core.ports.outbound.PedidoRepositorioPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoRepositorioEmMemoriaAdapter implements PedidoRepositorioPort {

    @Override
    public boolean salvar(Pedido pedido) {
        if (RepositorioEmMemoriaDados.pedidos.stream().noneMatch(p -> p.getId() == pedido.getId())) {
            return RepositorioEmMemoriaDados.pedidos.add(pedido);
        }
        return false;
    }

    @Override
    public boolean atualizar(Pedido pedido) {
        Optional<Pedido> existingPedido = RepositorioEmMemoriaDados.pedidos.stream()
                .filter(p -> p.getId() == pedido.getId())
                .findFirst();
        if (existingPedido.isPresent()) {
            int index = RepositorioEmMemoriaDados.pedidos.indexOf(existingPedido.get());
            RepositorioEmMemoriaDados.pedidos.set(index, pedido);
            return true;
        }
        return false;
    }

    @Override
    public Pedido buscarPorId(long pedidoId) {
        return RepositorioEmMemoriaDados.pedidos.stream()
                .filter(p -> p.getId() == pedidoId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean cancelar(long pedidoId) {
        return RepositorioEmMemoriaDados.pedidos.removeIf(pedido -> pedido.getId() == pedidoId);
    }
}