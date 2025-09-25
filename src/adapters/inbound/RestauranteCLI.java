package adapters.inbound;

import core.domain.Cliente;
import core.domain.Endereco;
import core.domain.ItemPedido;
import core.domain.Pedido;
import core.domain.Produto;
import core.ports.inbound.ClientePort;
import core.ports.inbound.EnderecoPort;
import core.ports.inbound.PedidoPort;
import core.ports.inbound.ProdutoPort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestauranteCLI {

    private final ClientePort clienteService;
    private final ProdutoPort produtoService;
    private final PedidoPort pedidoService;
    private final EnderecoPort enderecoService;
    private final Scanner scanner;

    public RestauranteCLI(ClientePort clienteService, ProdutoPort produtoService, PedidoPort pedidoService, EnderecoPort enderecoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
        this.enderecoService = enderecoService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            processarOpcao(opcao);
        } while (opcao != 0);
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Cadastrar Cliente");
        System.out.println("4. Consultar Cliente");
        System.out.println("5. Criar Pedido");
        System.out.println("6. Consultar Pedido");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarProduto();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                cadastrarCliente();
                break;
            case 4:
                consultarCliente();
                break;
            case 5:
                criarPedido();
                break;
            case 6:
                consultarPedido();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void adicionarProduto() {
        System.out.print("ID do Produto: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço do Produto: ");
        float preco = scanner.nextFloat();
        scanner.nextLine();

        Produto produto = new Produto(id, nome, preco);
        if (produtoService.adicionarProduto(produto)) {
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Falha ao adicionar produto.");
        }
    }

    private void listarProdutos() {
        System.out.println("--- Produtos ---");
        List<Produto> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(p -> System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome() + ", Preço: R$" + p.getPreco()));
        }
    }

    private void cadastrarCliente() {
        System.out.print("ID do Cliente: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Email do Cliente: ");
        String email = scanner.nextLine();

        // Endereço não é tratado aqui para simplificar a CLI de exemplo
        Cliente cliente = new Cliente(id, nome, email, null);
        if (clienteService.cadastrarCliente(cliente)) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar cliente. ID já existe ou erro.");
        }
    }

    private void consultarCliente() {
        System.out.print("ID do Cliente: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Cliente cliente = clienteService.consultarCliente(id);
        if (cliente != null) {
            System.out.println("--- Dados do Cliente ---");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void criarPedido() {
        System.out.print("ID do Pedido: ");
        long idPedido = scanner.nextLong();
        scanner.nextLine();

        System.out.print("ID do Cliente: ");
        long idCliente = scanner.nextLong();
        scanner.nextLine();

        Cliente cliente = clienteService.consultarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado. Por favor, cadastre o cliente primeiro.");
            return;
        }

        List<ItemPedido> itens = new ArrayList<>();
        char continuar = 's';
        while (continuar == 's') {
            System.out.print("ID do Produto: ");
            long idProduto = scanner.nextLong();
            scanner.nextLine();

            Produto produto = produtoService.listarProdutos().stream()
                    .filter(p -> p.getId() == idProduto)
                    .findFirst()
                    .orElse(null);

            if (produto != null) {
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                itens.add(new ItemPedido(produto, quantidade));
            } else {
                System.out.println("Produto não encontrado.");
            }

            System.out.print("Adicionar outro item? (s/n): ");
            continuar = scanner.nextLine().charAt(0);
        }

        Pedido novoPedido = new Pedido(
                idPedido,
                cliente,
                itens,
                PedidoPort.Status.AGUARDANDO,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        if (pedidoService.criarPedido(novoPedido) != null) {
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Falha ao criar pedido.");
        }
    }

    private void consultarPedido() {
        System.out.print("ID do Pedido: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Pedido pedido = pedidoService.consultarPedido(id);
        if (pedido != null) {
            System.out.println("--- Dados do Pedido ---");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente().getNome());
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Valor Total: R$" + String.format("%.2f", pedido.getTotal()));
            System.out.println("Itens:");
            pedido.getItens().forEach(item -> System.out.println("- " + item.getProduto().getNome() + " (x" + item.getQuantidade() + ")"));
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }
}