package core.services;

import core.domain.Cliente;
import core.ports.inbound.ClientePort;
import core.ports.outbound.ClienteRepositorioPort;
import core.ports.outbound.NotificacaoPort;

public class ClienteService implements ClientePort {

    private final ClienteRepositorioPort clienteRepository;
    private final NotificacaoPort notificacaoService;

    public ClienteService(ClienteRepositorioPort clienteRepository, NotificacaoPort notificacaoService) {
        this.clienteRepository = clienteRepository;
        this.notificacaoService = notificacaoService;
    }

    @Override
    public boolean cadastrarCliente(Cliente cliente) {
        if (clienteRepository.salvar(cliente)) {
            notificacaoService.enviarNotificacao(cliente.getId(), "Cadastro concluído com sucesso!");
            return true;
        }
        return false;
    }

    @Override
    public boolean atualizarCliente(Cliente cliente) {
        if (clienteRepository.atualizar(cliente)) {
            notificacaoService.enviarNotificacao(cliente.getId(), "Dados atualizados com sucesso!");
            return true;
        }
        return false;
    }

    @Override
    public Cliente consultarCliente(long id) {
        return clienteRepository.buscarPorId(id);
    }
}