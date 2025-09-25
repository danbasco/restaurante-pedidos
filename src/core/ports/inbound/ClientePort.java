package core.ports.inbound;

import core.domain.Cliente;

public interface ClientePort {

    public boolean cadastrarCliente(Cliente cliente);
    public boolean atualizarCliente(Cliente cliente);
    public Cliente consultarCliente(long id);

}
