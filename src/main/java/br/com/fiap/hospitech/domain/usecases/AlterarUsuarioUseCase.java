package br.com.fiap.hospitech.domain.usecases;

import br.com.fiap.hospitech.domain.entities.Usuario;

public interface AlterarUsuarioUseCase {
    Usuario handle(AlterarUsuarioCommand command);

    record AlterarUsuarioCommand(
            String id,
            String nome,
            String email,
            String login,
            Boolean ativo,
            String endereco,
            Usuario.Role role
    ) {}
}