package adapters.outbound;

import core.domain.Cliente;
import core.ports.outbound.ClienteRepositorioPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepositorioEmMemoriaAdapter implements ClienteRepositorioPort {

    private static final List<Cliente> clientes = new ArrayList<>();

    @Override
    public boolean salvar(Cliente cliente) {
        if (clientes.stream().noneMatch(c -> c.getId() == cliente.getId())) {
            return clientes.add(cliente);
        }
        return false;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        Optional<Cliente> existingCliente = clientes.stream()
                .filter(c -> c.getId() == cliente.getId())
                .findFirst();
        if (existingCliente.isPresent()) {
            int index = clientes.indexOf(existingCliente.get());
            clientes.set(index, cliente);
            return true;
        }
        return false;
    }

    @Override
    public Cliente buscarPorId(long clienteId) {
        return clientes.stream()
                .filter(c -> c.getId() == clienteId)
                .findFirst()
                .orElse(null);
    }
}