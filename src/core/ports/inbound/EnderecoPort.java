package core.ports.inbound;

import core.domain.Cliente;
import core.domain.Endereco;

public interface EnderecoPort {

    public boolean adicionarEnderecoAoCliente(Cliente cliente, Endereco endereco);
    public boolean atualizarEnderecoDoCliente(Cliente cliente, Endereco endereco);
    public Endereco consultarEnderecoDoCliente(Cliente cliente);

}
