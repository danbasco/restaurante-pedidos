package adapters.outbound;

import core.ports.outbound.NotificacaoPort;

// Adaptador de notificação (simula um serviço de envio de mensagens) [cite: 105, 106]
public class MockNotificacaoAdapter implements NotificacaoPort {

    @Override
    public boolean enviarNotificacao(long clienteId, String mensagem) {
        // Lógica de simulação de envio de notificação
        System.out.println("Enviando notificação para o cliente ID: " + clienteId);
        System.out.println("Mensagem: " + mensagem);
        return true; // Simula sucesso
    }
}