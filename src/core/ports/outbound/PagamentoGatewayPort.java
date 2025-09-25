package core.ports.outbound;

import core.domain.Pagamento;
import core.domain.Pedido;

public interface PagamentoGatewayPort {
    Pagamento processarPagamento(Pedido pedido, Pagamento pagamento);
    Pagamento consultarStatus(String idPagamento);
}
