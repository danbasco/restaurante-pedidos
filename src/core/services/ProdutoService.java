package core.services;

import core.domain.Produto;
import core.ports.inbound.ProdutoPort;
import core.ports.outbound.ProdutoRepositorioPort;

import java.util.List;

public class ProdutoService implements ProdutoPort {

    private final ProdutoRepositorioPort produtoRepository;

    public ProdutoService(ProdutoRepositorioPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public boolean adicionarProduto(Produto produto) {
        return produtoRepository.salvar(produto);
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.buscarTodos();
    }

    @Override
    public boolean atualizarProduto(Produto produto) {
        return produtoRepository.atualizar(produto);
    }

    @Override
    public boolean removerProduto(Produto produto) {
        return produtoRepository.remover(produto.getId());
    }
}