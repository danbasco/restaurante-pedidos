package core.ports.inbound;

import core.domain.Produto;

import java.util.List;

public interface ProdutoPort {

    public boolean adicionarProduto(Produto produto);
    public List<Produto> listarProdutos();
    public boolean atualizarProduto(Produto produto);
    public boolean removerProduto(Produto produto);


}
