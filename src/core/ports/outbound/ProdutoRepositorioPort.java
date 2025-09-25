package core.ports.outbound;

import core.domain.Produto;

import java.util.List;

public interface ProdutoRepositorioPort {

    public boolean salvar(Produto produto);
    public List<Produto> buscarTodos();
    public boolean atualizar(Produto produto);
    public boolean remover(long produtoId);

}