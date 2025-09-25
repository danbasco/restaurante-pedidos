package adapters.outbound;

import core.domain.Pedido;
import core.domain.Produto;
import core.ports.outbound.PedidoRepositorioPort;
import core.ports.outbound.ProdutoRepositorioPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Adaptador de persistência (em memória) para Pedidos e Produtos
public class RepositorioEmMemoriaAdapter implements PedidoRepositorioPort, ProdutoRepositorioPort {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<Produto> produtos = new ArrayList<>();

    // Implementação de PedidoRepositoryPort
    @Override
    public boolean salvar(Pedido pedido) {
        if (pedidos.stream().noneMatch(p -> p.getId() == pedido.getId())) {
            return pedidos.add(pedido);
        }
        return false;
    }

    @Override
    public boolean atualizar(Pedido pedido) {
        Optional<Pedido> existingPedido = pedidos.stream()
                .filter(p -> p.getId() == pedido.getId())
                .findFirst();
        if (existingPedido.isPresent()) {
            int index = pedidos.indexOf(existingPedido.get());
            pedidos.set(index, pedido);
            return true;
        }
        return false;
    }

    @Override
    public Pedido buscarPorId(long pedidoId) {
        return pedidos.stream()
                .filter(p -> p.getId() == pedidoId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean cancelar(long pedidoId) {
        return pedidos.removeIf(pedido -> pedido.getId() == pedidoId);
    }

    // Implementação de ProdutoRepositoryPort
    @Override
    public boolean salvar(Produto produto) {
        if (produtos.stream().noneMatch(p -> p.getId() == produto.getId())) {
            return produtos.add(produto);
        }
        return false;
    }

    @Override
    public List<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }

    @Override
    public boolean atualizar(Produto produto) {
        Optional<Produto> existingProduto = produtos.stream()
                .filter(p -> p.getId() == produto.getId())
                .findFirst();
        if (existingProduto.isPresent()) {
            int index = produtos.indexOf(existingProduto.get());
            produtos.set(index, produto);
            return true;
        }
        return false;
    }

    @Override
    public boolean remover(long produtoId) {
        return produtos.removeIf(p -> p.getId() == produtoId);
    }
}