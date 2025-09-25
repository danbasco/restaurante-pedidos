package core.ports.outbound;

import java.util.Optional;
import core.domain.Cliente;

public interface ClienteRepositorioPort {
    // retorna se houve ou nao sucesso ao salvar o cliente no DB
    public boolean salvar(Cliente cliente);

    // retorna se houve ou nao sucesso ao atualizar o cliente no DB
    public boolean atualizar(Cliente cliente);

    // retorna se houve ou nao sucesso ao atualizar o cliente no DB
    public Optional<Cliente> buscarPorId(long idCliente);

}
