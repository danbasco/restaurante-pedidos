package adapters.outbound;

import core.domain.Cliente;
import core.domain.Pedido;
import core.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEmMemoriaDados {
    public static final List<Cliente> clientes = new ArrayList<>();
    public static final List<Produto> produtos = new ArrayList<>();
    public static final List<Pedido> pedidos = new ArrayList<>();
}