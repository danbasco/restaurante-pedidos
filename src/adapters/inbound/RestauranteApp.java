package adapters.inbound;

import adapters.inbound.RestauranteCLI;
import adapters.outbound.MockNotificacaoAdapter;
import adapters.outbound.MockPagamentoAdapter;
import adapters.outbound.ClienteRepositorioEmMemoriaAdapter;
import adapters.outbound.PedidoRepositorioEmMemoriaAdapter;
import adapters.outbound.ProdutoRepositorioEmMemoriaAdapter;
import core.ports.outbound.PedidoRepositorioPort;
import core.services.ClienteService;
import core.services.EnderecoService;
import core.services.PedidoService;
import core.services.ProdutoService;

public class RestauranteApp {
    public static void main(String[] args) {
        System.out.println("Iniciando a aplicação Restaurante...");

        // Criação dos adaptadores de saída (Driven Adapters)
        ClienteRepositorioEmMemoriaAdapter clienteRepoAdapter = new ClienteRepositorioEmMemoriaAdapter();
        ProdutoRepositorioEmMemoriaAdapter produtoRepoAdapter = new ProdutoRepositorioEmMemoriaAdapter();
        PedidoRepositorioEmMemoriaAdapter pedidoRepoAdapter = new PedidoRepositorioEmMemoriaAdapter();
        MockPagamentoAdapter pagamentoAdapter = new MockPagamentoAdapter();
        MockNotificacaoAdapter notificacaoAdapter = new MockNotificacaoAdapter();

        // Criação dos serviços (núcleo da aplicação), injetando as dependências
        ClienteService clienteService = new ClienteService(clienteRepoAdapter, notificacaoAdapter);
        ProdutoService produtoService = new ProdutoService(produtoRepoAdapter);
        PedidoService pedidoService = new PedidoService((PedidoRepositorioPort) pedidoRepoAdapter, pagamentoAdapter, notificacaoAdapter);
        EnderecoService enderecoService = new EnderecoService(clienteRepoAdapter, notificacaoAdapter);

        // Criação do adaptador de entrada (Driving Adapter), injetando os serviços
        RestauranteCLI cli = new RestauranteCLI(clienteService, produtoService, pedidoService, enderecoService);

        // Inicia a interface de linha de comando
        cli.iniciar();
    }
}