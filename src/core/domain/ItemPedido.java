package core.domain;

import java.math.BigDecimal;
import java.util.UUID;

// A classe ItemPedido representa um item dentro de um Pedido.
// Ela é uma entidade de domínio e não deve ter dependências externas.
public class ItemPedido {

    private UUID id;
    private Produto produto;
    private int quantidade;
    private BigDecimal valorTotal;

    // Construtor
    public ItemPedido(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.id = UUID.randomUUID();
        this.produto = produto;
        this.quantidade = quantidade;
        this.calcularValorTotal();
    }

    // Método de comportamento (Lógica de negócio)
    // Conforme o documento, a lógica de calcular o total do item
    // deve ficar no núcleo.
    public void calcularValorTotal() {
        BigDecimal precoProduto = new BigDecimal(this.produto.getPreco());
        this.valorTotal = precoProduto.multiply(new BigDecimal(this.quantidade));
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    // Métodos para atualizar a quantidade, se necessário
    public void setQuantidade(int novaQuantidade) {
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantidade = novaQuantidade;
        this.calcularValorTotal(); // Recalcula o valor total
    }
}
