package br.com.fiap.hospitech.domain.usecases;

import br.com.fiap.hospitech.domain.entities.Usuario;

public interface CriarUsuarioUseCase {
    Usuario handle(CriarUsuarioCommand command);

    record CriarUsuarioCommand(
            String nome,
            String email,
            String login,
            Boolean ativo,
            String senha,
            String endereco,
            Usuario.Role role
    ) {}
}