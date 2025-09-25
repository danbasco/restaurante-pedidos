package core.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// A classe Pedido é uma entidade de domínio.
// Ela não tem dependências de frameworks ou tecnologias externas.
public class Pedido {

    private UUID id;
    private List<ItemPedido> itens;
    private Pagamento pagamento;
    private String status;
    private BigDecimal valorTotal;

    // Construtor
    public Pedido() {
        this.id = UUID.randomUUID();
        this.itens = new ArrayList<>();
        this.status = "CRIADO";
        this.valorTotal = BigDecimal.ZERO;
    }

    // Métodos de comportamento (Lógica de negócio)
    // Conforme o documento, a lógica de calcular o total fica no núcleo.
    public void calcularTotal() {
        this.valorTotal = BigDecimal.ZERO;
        for (ItemPedido item : this.itens) {
            this.valorTotal = this.valorTotal.add(item.getValorTotal());
        }
    }

    // Conforme o documento, a lógica de alterar o status do pedido também fica no núcleo.
    public void alterarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    // Métodos para adicionar/remover itens, conforme o documento[cite: 39, 40].
    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        this.calcularTotal(); // Recalcula o total ao adicionar um item
    }

    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
        this.calcularTotal(); // Recalcula o total ao remover um item
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    // Getter e Setter para o Pagamento
    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
