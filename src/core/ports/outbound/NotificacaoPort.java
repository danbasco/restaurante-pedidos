package core.ports.outbound;

public interface NotificacaoPort {

    public boolean enviarNotificacao(long clienteId, String mensagem);
}