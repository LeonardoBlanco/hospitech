package br.com.fiap.hospitech.infra.notifications;

import br.com.fiap.hospitech.infra.events.UsuarioCriadoEvent;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
public class NotificationListener {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationListener.class);

    @EventListener
    public void onUsuarioCriado(UsuarioCriadoEvent event) {
        // Aqui você integraria com RabbitMQ/Kafka ou serviço de e-mail/SMS.
        // Exemplo simples de log:
        LOG.info("Evento recebido: UsuarioCriado -> id: {}, email: {}", event.getUsuarioId(), event.getEmail());

        // Simulação: gerar lembrete futuro, agendar envio, etc.
    }
}