package adapters.outbound;

import core.domain.Pedido;
import core.ports.outbound.PagamentoGatewayPort;
import java.util.Map;

// Adaptador de pagamento (simula um gateway de pagamento) [cite: 103, 104]
public class MockPagamentoAdapter implements PagamentoGatewayPort {

    @Override
    public boolean processarPagamento(Pedido pedido, Map<String, String> dadosPagamento) {
        // Lógica de simulação de processamento de pagamento
        System.out.println("Processando pagamento para o pedido ID: " + pedido.getId());
        System.out.println("Dados do pagamento: " + dadosPagamento);
        return true; // Simula sucesso
    }

    @Override
    public String consultarStatus(String pagamentoId) {
        // Lógica de simulação de consulta de status de pagamento
        System.out.println("Consultando status do pagamento ID: " + pagamentoId);
        return "APROVADO"; // Simula status aprovado
    }
}