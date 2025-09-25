package core.ports.outbound;

import core.domain.Pagamento;
import core.domain.Pedido;

import java.util.Map;

public interface PagamentoGatewayPort {
    public boolean processarPagamento(Pedido pedido, Map<String, String> dadosPagamento);
    public String consultarStatus(String pagamentoId);
}
