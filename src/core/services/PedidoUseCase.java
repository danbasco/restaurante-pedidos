package core.services;

import core.domain.Pedido;
import core.ports.inbound.PedidoPort;
import core.ports.outbound.PagamentoGatewayPort;
import core.ports.outbound.PedidoRepositorioPort;

import java.util.UUID;

public class PedidoUseCase implements PedidoPort {

    private final PedidoRepositorioPort pedidoRepositorio;
    private final PagamentoGatewayPort pagamentoGateway;

    // A classe depende das interfaces, não das implementações.
    public PedidoUseCase(PedidoRepositorioPort pedidoRepositorio, PagamentoGatewayPort pagamentoGateway) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        // Lógica de negócio: validar o pedido, calcular o total, etc.
        pedido.calcularTotal();

        // Chama o driven port para processar o pagamento
        // Não importa se é Stripe, PayPal ou um mock.
        this.pagamentoGateway.processarPagamento(pedido, pedido.getPagamento());

        // Salva o pedido usando o repositório
        return this.pedidoRepositorio.salvar(pedido);
    }

    // Outros métodos como atualizarPedido, consultarStatusPedido, etc.
    @Override
    public void atualizarPedido(UUID pedidoId, Pedido pedidoAtualizado) {
        // Implementação...
    }

    @Override
    public Status consultarStatusPedido(UUID pedidoId) {
        // Implementação...
        return "status";
    }

    @Override
    public void cancelarPedido(UUID pedidoId) {
        // Implementação...
    }
}
