package core.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Pagamento {

    private UUID id;
    private String status;
    private Pedido pedido;
    private float valor;

    // Construtores
    public Pagamento(Pedido pedido) {
        this.id = UUID.randomUUID();
        this.pedido = pedido;
        this.valor = pedido.getTotal();
        this.status = "PENDENTE";
    }

    public Pagamento(String status) {
        this.status = status;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public float getValor() {
        return valor;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    // Métodos de comportamento (lógica de domínio)
    public boolean isAprovado() {
        return "APROVADO".equals(this.status);
    }

    public boolean isPendente() {
        return "PENDENTE".equals(this.status);
    }

}
