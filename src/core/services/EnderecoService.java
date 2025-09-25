package core.services;

import core.domain.Cliente;
import core.domain.Endereco;
import core.ports.inbound.EnderecoPort;
import core.ports.outbound.NotificacaoPort;
import core.ports.outbound.ClienteRepositorioPort; // Assumindo que a manipulação de endereço está ligada ao cliente

public class EnderecoService implements EnderecoPort {

    private final ClienteRepositorioPort clienteRepository;
    private final NotificacaoPort notificacaoService;

    public EnderecoService(ClienteRepositorioPort clienteRepository, NotificacaoPort notificacaoService) {
        this.clienteRepository = clienteRepository;
        this.notificacaoService = notificacaoService;
    }

    @Override
    public boolean adicionarEnderecoAoCliente(Cliente cliente, Endereco endereco) {
        cliente.setEndereco(endereco);
        if (clienteRepository.atualizar(cliente)) {
            notificacaoService.enviarNotificacao(cliente.getId(), "Seu endereço foi adicionado com sucesso!");
            return true;
        }
        return false;
    }

    @Override
    public boolean atualizarEnderecoDoCliente(Cliente cliente, Endereco endereco) {
        cliente.setEndereco(endereco);
        if (clienteRepository.atualizar(cliente)) {
            notificacaoService.enviarNotificacao(cliente.getId(), "Seu endereço foi atualizado!");
            return true;
        }
        return false;
    }

    @Override
    public Endereco consultarEnderecoDoCliente(Cliente cliente) {
        // A lógica de consulta de endereço é simplificada, pois o Endereco é um campo de Cliente.
        Cliente clienteComEndereco = clienteRepository.buscarPorId(cliente.getId());
        return clienteComEndereco != null ? clienteComEndereco.getEndereco() : null;
    }
}