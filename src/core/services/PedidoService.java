package core.services;

import core.domain.ItemPedido;
import core.domain.Pedido;
import core.ports.inbound.PedidoPort;
import core.ports.outbound.NotificacaoPort;
import core.ports.outbound.PagamentoGatewayPort;
import core.ports.outbound.PedidoRepositorioPort;
import java.util.List;

public class PedidoService implements PedidoPort {

    private final PedidoRepositorioPort pedidoRepository;
    private final PagamentoGatewayPort pagamentoGateway;
    private final NotificacaoPort notificacaoService;

    public PedidoService(PedidoRepositorioPort pedidoRepository, PagamentoGatewayPort pagamentoGateway, NotificacaoPort notificacaoService) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoGateway = pagamentoGateway;
        this.notificacaoService = notificacaoService;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        if (pedidoRepository.salvar(pedido)) {
            notificacaoService.enviarNotificacao(pedido.getCliente().getId(), "Seu pedido foi criado!");
            return pedido;
        }
        return null;
    }

    @Override
    public Pedido atualizarPedido(Pedido pedido) {
        if (pedidoRepository.atualizar(pedido)) {
            notificacaoService.enviarNotificacao(pedido.getCliente().getId(), "Seu pedido foi atualizado!");
            return pedido;
        }
        return null;
    }

    @Override
    public Pedido consultarPedido(long id) {
        return pedidoRepository.buscarPorId(id);
    }

    @Override
    public List<Pedido> listarPedidos() {
        // Implementação para listar todos os pedidos, que não está na interface PedidoRepositorioPort fornecida.
        // Será necessário adicionar um método para isso na interface PedidoRepositorioPort.
        return null;
    }

    @Override
    public boolean cancelarPedido(long id) {
        Pedido pedido = pedidoRepository.buscarPorId(id);
        if (pedido != null) {
            pedido.setStatus(Status.CANCELADO);
            if (pedidoRepository.atualizar(pedido)) {
                notificacaoService.enviarNotificacao(pedido.getCliente().getId(), "Seu pedido foi cancelado.");
                return true;
            }
        }
        return false;
    }
}