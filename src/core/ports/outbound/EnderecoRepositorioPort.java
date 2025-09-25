package core.ports.outbound;

import core.domain.Endereco;

public interface EnderecoRepositorioPort {
    //retorna se houve sucesso ao adicionar endereco ao cliente
    public boolean adicionarEnderecoAoCliente(Endereco endereco);
    public boolean atualizarEndereco();
    public boolean consultarEndereco();
}
