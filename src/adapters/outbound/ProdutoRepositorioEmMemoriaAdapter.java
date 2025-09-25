package adapters.outbound;

import core.domain.Produto;
import core.ports.outbound.ProdutoRepositorioPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositorioEmMemoriaAdapter implements ProdutoRepositorioPort {

    private static final List<Produto> produtos = new ArrayList<>();

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
    public Produto buscarPorId(long produtoId) {
        return RepositorioEmMemoriaDados.produtos.stream()
                .filter(p -> p.getId() == produtoId)
                .findFirst()
                .orElse(null);
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