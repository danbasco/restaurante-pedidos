package core.ports.inbound;

import core.domain.Cliente;

public interface EnderecoPort {

    public boolean adicionarEnderecoAoCliente(Cliente cliente);
    public boolean atualizarEndereco(Cliente cliente);
    public boolean consultarEndereco(Cliente cliente);

}
