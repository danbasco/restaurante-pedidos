package adapters.inbound;

import core.domain.Cliente;
import core.domain.Endereco;
import core.domain.ItemPedido;
import core.domain.Pedido;
import core.domain.Produto;
import core.ports.inbound.PedidoPort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteApp {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Exemplo de uso das classes e interfaces
        System.out.println("--- Exemplo de Uso ---");

        // Criando alguns produtos
        Produto produto1 = new Produto(1, "Pizza Margherita", 45.0f);
        Produto produto2 = new Produto(2, "Lasanha de Carne", 55.0f);

        // Criando um cliente
        Endereco enderecoCliente = new Endereco(12345678, "Rua das Flores", 100, "Apartamento 501");
        Cliente cliente = new Cliente(101, "João Silva", "joao.silva@email.com", enderecoCliente);

        // Criando itens do pedido
        List<ItemPedido> itensDoPedido = new ArrayList<>();
        itensDoPedido.add(new ItemPedido(produto1, 2));
        itensDoPedido.add(new ItemPedido(produto2, 1));

        // Criando um pedido
        Pedido pedido = new Pedido(
                1,
                cliente,
                itensDoPedido,
                PedidoPort.Status.AGUARDANDO,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        System.out.println("Pedido criado com sucesso!");
        System.out.println("ID do Pedido: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Valor Total: R$" + String.format("%.2f", pedido.getTotal()));
    }
}