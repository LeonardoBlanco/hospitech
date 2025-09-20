package br.com.fiap.hospitech.domain.service;

import br.com.fiap.hospitech.domain.entities.Usuario;
import br.com.fiap.hospitech.domain.usecases.CriarUsuarioUseCase;
import br.com.fiap.hospitech.domain.usecases.ObterUsuarioUseCase;
import br.com.fiap.hospitech.infra.events.UsuarioCriadoEvent;
import br.com.fiap.hospitech.infra.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements CriarUsuarioUseCase, ObterUsuarioUseCase {

    private final UsuarioRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public UsuarioService(UsuarioRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Usuario handle(CriarUsuarioUseCase.CriarUsuarioCommand command) {
        // Cria entidade (inclui id)
        var usuario = Usuario.incluir(command);

        // Aqui você geralmente criptografa a senha (ex: BCrypt). Vou deixar comentário:
        // usuario.alterarSenha(passwordEncoder.encode(usuario.getSenha()));
        // para manter o padrão do seu exemplo, não apliquei o encoder automaticamente.

        // Persiste
        var saved = repository.save(usuario);

        // Dispara evento (pode ser substituído por envio para RabbitMQ/Kafka)
        var event = new UsuarioCriadoEvent(saved.getId(), saved.getEmail());
        eventPublisher.publishEvent(event);

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario handle(String id) {
        Optional<Usuario> found = repository.findById(id);
        return found.orElse(null); // pode lançar exceção se preferir
    }
}